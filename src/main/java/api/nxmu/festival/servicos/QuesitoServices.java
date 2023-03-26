package api.nxmu.festival.servicos;

import java.util.List;
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

    public Quesito encontrarPorId(Long id){        
        return quesitoDB.findById(id).get();
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
    
}
