package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ClassificacaoDto;
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

    public Optional<Classificacao> encontrarPorApresentacao(Long id){        
        return classificacaoDB.findByIdApresentacao(id);
    }

    public List<ClassificacaoDto> encontrar(){
        List<ClassificacaoDto> listaDto = new ArrayList<ClassificacaoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Classificacao classificacao: classificacaoDB.findAll()) {
            listaDto.add(new ClassificacaoDto(
                classificacao.getId(), classificacao.getNotafinal(),
                classificacao.getCategoria().getId(), classificacao.getApresentacao().getId()));
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

    public void calcularClassificacao(long codigoCategoria){
            // Recebe o identificador código da apresentação e transforma optional em objeto apresentacao

            // Confere se a classificacao ja existe para esta apresentacao -- se ja houver classificacao retorn e sai da operação
        // System.out.println(encontrarPorApresentacao(apresentacao.getId()));
            //return;

            // Retornar lista de notas pertencentes a apresentação
        List<NotaFinal> notasApresentacao = notaFinalServices.encontrarPorCategoria(codigoCategoria);
        if(!(notasApresentacao.size() > 0))
            return;

            // Itera por todas as notas pertencentes a mesma apresentacao   --- não diferencia jurados nem quesitos
        // Realiza cálculo da média final da nota
        double media = 0;
        for (NotaFinal nota : notasApresentacao) {
            media  += nota.getNotaFinal();        
        }

        media = (media / notasApresentacao.size());
        System.out.println(media);


        // Após media calcula monta o objeto nota final
        Classificacao classificacao = Classificacao.builder()
        .notafinal(media)   
        .categoria(notasApresentacao.get(0).getCategoria()) 
        .apresentacao(notasApresentacao.get(0).getApresentacao())
        .build();

        // classificacaoDB.save(classificacao); 
        System.out.println(classificacao);       

    }
    
}
