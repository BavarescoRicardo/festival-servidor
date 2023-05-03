package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.QuesitoDto;
import api.nxmu.festival.modelo.Quesito;
import api.nxmu.festival.repositorio.QuesitoRepositorio;

@Service
public class QuesitoServices {

    @Autowired
    private QuesitoRepositorio quesitoDB;

    public Optional<Quesito> encontrarPorId(Long id){        
        return quesitoDB.findById(id);
    }

    public List<QuesitoDto> encontrar(){
        List<QuesitoDto> listaDto = new ArrayList<QuesitoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Quesito quesito: quesitoDB.findAll()) {
            listaDto.add(new QuesitoDto(
                quesito.getId(), quesito.getDescricao(), quesito.getPeso()));
        }

        return listaDto;
    }

    public boolean salvar(QuesitoDto quesito){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Quesito e = new Quesito(
                quesito.getDescricao(), quesito.getPeso());

            this.quesitoDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public QuesitoDto atualizar(QuesitoDto quesito, long id){
        try {
            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Quesito e = this.encontrarPorId(id).get();
            e.setDescricao(quesito.getDescricao());
            e.setPeso(quesito.getPeso());

            this.quesitoDB.save(e);    
        } catch (Exception e) {
            return null;
        }
        return quesito;
    }    
}
