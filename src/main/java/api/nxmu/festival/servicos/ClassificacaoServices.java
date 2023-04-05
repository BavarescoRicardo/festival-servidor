package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ClassificacaoDto;
import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.modelo.Classificacao;
import api.nxmu.festival.modelo.Nota;
import api.nxmu.festival.repositorio.ClassificacaoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassificacaoServices {

    @Autowired
    private ClassificacaoRepositorio classificacaoDB;

    private final CategoriaServices categoriaServices;
    private final ApresentacaoServices apresentacaoServices;
    private final NotaServices notaServices;

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

    public void calcularNotaFinal(long codigoApresentacao){
        // Recebe o identificador código da apresentação e transforma optional em objeto apresentacao
    Apresentacao apresentacao = apresentacaoServices.encontrarPorId(codigoApresentacao).get();
    System.out.println(apresentacao.getMusica());
        // Confere se a classificacao ja existe para esta apresentacao -- se ja houver classificacao retorn e sai da operação
    // System.out.println(encontrarPorApresentacao(apresentacao.getId()));
        //return;

        // Retornar lista de notas pertencentes a apresentação
    List<Nota> notasApresentacao = notaServices.encontrarPorApresentacao(apresentacao.getId());
    if(!(notasApresentacao.size() > 0))
        return;

        // Itera por todas as notas pertencentes a mesma apresentacao   --- não diferencia jurados nem quesitos
    // Realiza cálculo da média final da nota
    double media = 0;
    for (Nota nota : notasApresentacao) {
        media  += nota.getNota();        
    }

    media = (media / notasApresentacao.size());
    System.out.println(media);

    // Adicionar condição para desconsider jurados que der nota mais alta e também o que der a nota mais baixa
    // O resultado do  calculo é a média de notas de todos os jurados e de todos os quesitos Ex..
        
        // -- for jurado 1 -- n
            // -- for quesito 1 -- n 
                // sum += notaQuesito
            // -- fim for quesitos
            // -- resultado sum dividido por numero de quesitos

        // -- fim for jurados

        // -- resultado sum dividido por numero de jurados
    // -- resultado final sera a média de notas da apresentação
}
    
}
