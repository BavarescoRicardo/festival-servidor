package api.nxmu.festival.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.AtualizaNotaDto;
import api.nxmu.festival.dto.HistoricoNotaDto;
import api.nxmu.festival.dto.NotaDto;
import api.nxmu.festival.dto.NotasDto;
import api.nxmu.festival.dto.TabelaNotaDto;
import api.nxmu.festival.dto.filtros.FiltroNotaDto;
import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.modelo.Nota;
import api.nxmu.festival.modelo.NotaFinal;
import api.nxmu.festival.modelo.Quesito;
import api.nxmu.festival.repositorio.NotaFinalRepositorio;
import api.nxmu.festival.repositorio.NotaRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepositorio notaDB;    
    private final NotaFinalRepositorio notaFinalDB;    
    private final NotaFinalService notaFinalService;

    private final JuradoService juradoService;
    private final ApresentacaoService apresentacaoService;
    private final QuesitoService quesitoService;

    public Optional<Nota> encontrarPorId(Long id){        
        return notaDB.findById(id);
    }

    public List<TabelaNotaDto> encontrar(){
        List<TabelaNotaDto> listaDto = new ArrayList<TabelaNotaDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Nota nota: notaDB.encontrarhistoricoNotas()) {
            listaDto.add(new TabelaNotaDto(
                nota.getId(), nota.getNota(),  
                nota.getApresentacao().getNomeartistico(), nota.getJurado().getNome(), 
                nota.getApresentacao().getMusica(), nota.getQuesito().getDescricao()));
        }

        return listaDto;
    }

    public List<TabelaNotaDto> encontrarFiltrado(FiltroNotaDto filtro){
        List<TabelaNotaDto> listaDto = new ArrayList<TabelaNotaDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 100);
        List<Nota> listaFiltrada = notaDB.
            findAllFiltrado(
                filtro.getCodCategoria(), pageable).getContent();        
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Nota nota: listaFiltrada) {
            listaDto.add(new TabelaNotaDto(
                nota.getId(), nota.getNota(),  
                nota.getApresentacao().getNomeartistico(), nota.getJurado().getNome(), 
                nota.getApresentacao().getMusica(), nota.getQuesito().getDescricao()));
        }

        return listaDto;
    }     

    public boolean salvar(NotaDto notaDto){
        try {
            // Validações
        	// Verifica se nota esta entre o maximo e o minimo valor da nota estipulado pelo festival
        	if(notaDto.getNota() < 5 || notaDto.getNota() > 10) {
        		throw new Exception("Nota com valor fora dos limites deste evento");
        	}
            // Verifica se a apresentacao pertence a categoria informada        	
            Apresentacao apresentacao = apresentacaoService.encontrarPorId(notaDto.getApresentacao()).get();
            if(apresentacao.getCategoria().getId() != notaDto.getCategoria()){
            	throw new Exception("Esta apresentacao nao pertence a esta categoria");
            }

            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Nota e =  Nota.builder()
                .nota(notaDto.getNota())
                .jurado(juradoService.encontrarPorId(notaDto.getJurado()).get())
                .apresentacao(apresentacaoService.encontrarPorId(notaDto.getApresentacao()).get())
                .quesito(quesitoService.encontrarPorId(notaDto.getQuesito()).get()).build();

            // Confere se ja existe nota para esta apresentacao este jurado e quesito
            long apr = notaDto.getApresentacao(); 
            long jur = notaDto.getJurado(); 
            long ques = notaDto.getQuesito(); 
            if (this.encontrarPorApresentacaoeJuradoQuesito(apr, jur, ques).size() > 0){
                e.setId(this.encontrarPorApresentacaoeJuradoQuesito(apr, jur, ques).get(0).getId());
            }

            this.notaDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean salvarNotas(NotasDto notaDto) {
        try {
            Apresentacao apresentacao = apresentacaoService.encontrarPorId(notaDto.getApresentacao()).orElseThrow();
            if (apresentacao.getCategoria().getId() != notaDto.getCategoria()) {
                throw new Exception("Esta apresentacao nao pertence a esta categoria");
            }

            notaDto.getNotasPorQuesito().forEach((idQuesito, valorNota) -> {
                if (valorNota < 5 || valorNota > 10) {
                    throw new RuntimeException("Nota fora do intervalo permitido (5 a 10)");
                }

                Nota nota = Nota.builder()
                        .nota(valorNota)
                        .jurado(juradoService.encontrarPorId(notaDto.getJurado()).orElseThrow())
                        .apresentacao(apresentacao)
                        .quesito(quesitoService.encontrarPorId(idQuesito).orElseThrow())
                        .build();

                // Atualiza nota existente, se houver
                var existentes = encontrarPorApresentacaoeJuradoQuesito(apresentacao.getId(), notaDto.getJurado(), idQuesito);
                if (!existentes.isEmpty()) {
                    nota.setId(existentes.get(0).getId());
                }

                notaDB.save(nota);
            });

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    
    public AtualizaNotaDto atualizar(AtualizaNotaDto notaDto, long id){
        try {

            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Nota nota =  this.encontrarPorId(id).get();
            
            // Remove nota final antes de alterar a nota
            this.notaFinalService.removerNotaFinal(nota.getApresentacao().getId(), nota.getJurado().getId());
            nota.setNota(notaDto.getNota());
            this.notaDB.save(nota);

            // Após atualizar a nota deve refazer o calculo da nota nofinal
            this.calcularNotaFinal(nota.getApresentacao().getId(), nota.getJurado().getId());
        } catch (Exception e) {
            return null;
        }
        return notaDto;
    }    

    public ResponseEntity<String> remover(long id){
        try {
            
            Nota nota = this.encontrarPorId(id).get();
            this.notaFinalService.removerNotaFinal(nota.getApresentacao().getId(), nota.getJurado().getId());
            this.notaDB.delete(nota);

            return ResponseEntity.ok().body("Removido objeto de id: "+id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }    
    
    public List<Nota> encontrarPorApresentacaoeJurado(long codigoApresentacao, long codigoJurado){        
        
        return  notaDB.findAllByApresentacaoJurado(codigoApresentacao, codigoJurado);
    }
    
    public List<Nota> encontrarPorApresentacaoeJuradoQuesito(long codigoApresentacao, long codigoJurado, long quesito){        
        
        return  notaDB.encontrarPorApresentacaoeJuradoQuesito(codigoApresentacao, codigoJurado, quesito);
    }     

    public void calcularNotaFinal(long codigoApresentacao, long codigoJurado){        

            // Retornar lista de notas pertencentes a apresentação
        List<Nota> notasApresentacao = encontrarPorApresentacaoeJurado(codigoApresentacao, codigoJurado);
        if(!(notasApresentacao.size() > 0))
            return;

            // Itera por todas as notas pertencentes a mesma apresentacao   --- não diferencia jurados nem quesitos
        // Realiza cálculo da média final da nota
        double media = 0;
        for (Nota nota : notasApresentacao) {
            media  += nota.getNota();        
        }

        media = (media / notasApresentacao.size());

        // Após media calcula monta o objeto nota final
        NotaFinal notaFinal = NotaFinal.builder()
        .notaFinal(media)   
        .jurado(notasApresentacao.get(0).getJurado())    
        .apresentacao(notasApresentacao.get(0).getApresentacao())
        .build();


        // Confere se a nota final ja existe para esta apresentacao -- se ja houver nota define id da nota e salva atualizando
        if (notaFinalService.encontrarPorApresentacaoeJurado(codigoApresentacao, codigoJurado).size() > 0){
            notaFinal.setId(notaFinalService.encontrarPorApresentacaoeJurado(codigoApresentacao, codigoJurado).get(0).getId());
        }
            
        notaFinalDB.save(notaFinal);
    }    

    public List<HistoricoNotaDto> encontrarHistoricoNotas(){       

	        // Retornar lista de notas pertencentes a apresentação
	    List<Nota> notasApresentacao = notaDB.encontrarhistoricoNotas();
	    if(!(notasApresentacao.size() > 0))
	        return null;
	
	    List<HistoricoNotaDto> listaHistoricoNotas = new ArrayList();
	    for(int idx = 0; idx < notasApresentacao.size() / 4; idx ++ ) {
		    HistoricoNotaDto historicoNotas = new HistoricoNotaDto(
		    		notasApresentacao.get(idx*4).getJurado().getNome(),
		    		notasApresentacao.get(idx*4).getApresentacao().getMusica()
				);
		
		    // atribui cada quesito ao historico 0 - 3 por apresentacao  + (idx*4)
		    historicoNotas.setNotaInterpretacao(notasApresentacao.get(0 + (idx*4)).getNota());
		    historicoNotas.setNotaRitmo(notasApresentacao.get(1 + (idx*4)).getNota());
		    historicoNotas.setNotaDiccao(notasApresentacao.get(2 + (idx*4)).getNota());
			historicoNotas.setNotaAfinacao(notasApresentacao.get(3 + (idx*4)).getNota());        
		    
			listaHistoricoNotas.add(historicoNotas);        
	    }
	    
	    return listaHistoricoNotas;
    }      
}
