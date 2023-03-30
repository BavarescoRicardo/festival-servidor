package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ClassificacaoDto;
import api.nxmu.festival.modelo.Classificacao;
import api.nxmu.festival.repositorio.ClassificacaoRepositorio;

@Service
public class ClassificacaoServices {

    @Autowired
    private ClassificacaoRepositorio classificacaoDB;

    public Optional<Classificacao> encontrarPorId(Long id){        
        return classificacaoDB.findById(id);
    }

    public List<ClassificacaoDto> encontrar(){
        List<ClassificacaoDto> listaDto = new ArrayList<ClassificacaoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Classificacao classificacao: classificacaoDB.findAll()) {
            listaDto.add(new ClassificacaoDto(
                classificacao.getId(), classificacao.getNotafinal(),
                classificacao.getCategoria(), classificacao.getApresentacao()));
        }

        return listaDto;
    }

    public boolean salvar(ClassificacaoDto classificacao){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Classificacao e = new Classificacao(
                classificacao.getNotafinal(), classificacao.getCategoria(), 
                classificacao.getApresentacao());

            this.classificacaoDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
