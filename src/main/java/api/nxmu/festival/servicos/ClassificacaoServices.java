package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ApresentacaoDto;
import api.nxmu.festival.dto.ClassificacaoDto;
import api.nxmu.festival.dto.ClassificacaoListaDto;
import api.nxmu.festival.modelo.Classificacao;
import api.nxmu.festival.modelo.NotaFinal;
import api.nxmu.festival.repositorio.ClassificacaoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassificacaoServices {

    @Autowired
    private ClassificacaoRepositorio classificacaoDB;

    private final CategoriaServices categoriaServices;
    private final ApresentacaoServices apresentacaoServices;
    private final NotaFinalServices notaFinalServices;

    public Optional<Classificacao> encontrarPorId(Long id){        
        return classificacaoDB.findById(id);
    }

    public Optional<Classificacao> encontrarPorApresentacao(long id){        
        return classificacaoDB.findByIdApresentacao(id);
    }

    public List<ClassificacaoListaDto> encontrar(){
        BigDecimal bd;
        List<ClassificacaoListaDto> listaDto = new ArrayList<ClassificacaoListaDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Classificacao classificacao: classificacaoDB.findAll()) {
            bd = new BigDecimal(classificacao.getNotafinal()).setScale(2,RoundingMode.HALF_DOWN);
            listaDto.add(new ClassificacaoListaDto(
                classificacao.getId(), bd.doubleValue(),
                classificacao.getCategoria().getDescricao(), classificacao.getApresentacao().getMusica()));
        }

        return listaDto;
    }

    public boolean salvar(ClassificacaoDto classificacao){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Classificacao e = new Classificacao(
                classificacao.getNotafinal(), 
                categoriaServices.encontrarPorId(classificacao.getCategoria()).get(), 
                apresentacaoServices.encontrarPorId(classificacao.getApresentacao()).get());

            this.classificacaoDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ClassificacaoDto atualizar(ClassificacaoDto classificacao, long id){
        try {
            // Encontra objeto salvo pelo id e depois atualiza
            Classificacao clas = this.encontrarPorId(id).get();
            clas.setNotafinal(classificacao.getNotafinal());
            clas.setCategoria(categoriaServices.encontrarPorId(classificacao.getCategoria()).get());
            clas.setApresentacao(apresentacaoServices.encontrarPorId(classificacao.getApresentacao()).get());

            this.classificacaoDB.save(clas);
            return classificacao;
        } catch (Exception e) {
            return null;
        }
    }
    
    public ResponseEntity<String> remover(long id){
        try {
            
            Classificacao clas = this.encontrarPorId(id).get();
            this.classificacaoDB.delete(clas);            

            return ResponseEntity.ok().body("Removido objeto de id: "+id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }   

    public void calcularClassificacao(long codigoCategoria){
        // Retornar lista de notas finais pertencentes a categoria
        List<ApresentacaoDto> apresentacoes = apresentacaoServices.encontrarPorCategoria(codigoCategoria);
        if(!(apresentacoes.size() > 0))
            return;

        // Itera por todas as notas pertencentes a mesma categoria   --- não diferencia jurados nem quesitos nem apresentações        
        double media = 0;

        for (ApresentacaoDto apresentacao : apresentacoes) { 
            List<NotaFinal> notasApresentacao = notaFinalServices.encontrarPorApresentacao(apresentacao.getCodigo());
            for (NotaFinal notaApresentacao : notasApresentacao) {
                media  += notaApresentacao.getNotaFinal();        
            }

            System.out.println(media);
            media = (media / notasApresentacao.size());            

            // Após media calcula monta o objeto classificação
            Classificacao classificacao = Classificacao.builder()
            .notafinal(media)   
            .categoria(categoriaServices.encontrarPorId(apresentacao.getCategoria()).get()) 
            .apresentacao(apresentacaoServices.encontrarPorId(apresentacao.getCodigo()).get())
            .build();
            
            if (!this.encontrarPorApresentacao(apresentacao.getCodigo()).isEmpty()){

                classificacao.setId(this.encontrarPorApresentacao(apresentacao.getCodigo()).get().getId());
            }

            classificacaoDB.save(classificacao); 
            media = 0;
        }

    }
    
}
