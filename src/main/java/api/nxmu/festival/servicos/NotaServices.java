package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.NotaDto;
import api.nxmu.festival.dto.TabelaNotaDto;
import api.nxmu.festival.modelo.Nota;
import api.nxmu.festival.modelo.NotaFinal;
import api.nxmu.festival.repositorio.NotaFinalRepositorio;
import api.nxmu.festival.repositorio.NotaRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotaServices {

    private final NotaRepositorio notaDB;    
    private final NotaFinalRepositorio notaFinalDB;    

    private final CategoriaServices categoriaServices;
    private final JuradoServices juradoServices;    
    private final ApresentacaoServices apresentacaoServices;
    private final QuesitoServices quesitoServices;
    private final NotaFinalServices notaFinalServices;

    public Optional<Nota> encontrarPorId(Long id){        
        return notaDB.findById(id);
    }

    public List<TabelaNotaDto> encontrar(){
        List<TabelaNotaDto> listaDto = new ArrayList<TabelaNotaDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Nota nota: notaDB.findAll()) {
            listaDto.add(new TabelaNotaDto(
                nota.getId(), nota.getNota(), nota.getCategoria().getDescricao(), 
                nota.getApresentacao().getNomeartistico(), nota.getJurado().getNome(), 
                nota.getApresentacao().getMusica(), nota.getQuesito().getDescricao()));
        }

        return listaDto;
    }

    public boolean salvar(NotaDto notaDto){
        try {

            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Nota e =  Nota.builder()
                .nota(notaDto.getNota())
                .categoria(categoriaServices.encontrarPorId(notaDto.getCategoria()).get())
                .jurado(juradoServices.encontrarPorId(notaDto.getJurado()).get())
                .apresentacao(apresentacaoServices.encontrarPorId(notaDto.getApresentacao()).get())
                .quesito(quesitoServices.encontrarPorId(notaDto.getQuesito())).build();
            this.notaDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }   
    
    public List<Nota> encontrarPorApresentacaoeJurado(long codigoApresentacao, long codigoJurado){        
        
        return  notaDB.findAllByApresentacaoJurado(codigoApresentacao, codigoJurado);
    }    

    public void calcularNotaFinal(long codigoApresentacao, long codigoJurado){

            // Confere se a classificacao ja existe para esta apresentacao -- se ja houver classificacao retorn e sai da operação
            if (notaFinalServices.encontrarPorApresentacaoeJurado(codigoApresentacao, codigoJurado).size() > 0)
                return;

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
        .categoria(notasApresentacao.get(0).getCategoria()) 
        .jurado(notasApresentacao.get(0).getJurado())    
        .apresentacao(notasApresentacao.get(0).getApresentacao())
        .build();

        notaFinalDB.save(notaFinal);

    }    

}
