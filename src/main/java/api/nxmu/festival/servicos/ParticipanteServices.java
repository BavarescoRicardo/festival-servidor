package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.modelo.Participante;
import api.nxmu.festival.repositorio.ParticipanteRepositorio;

@Service
public class ParticipanteServices {

    @Autowired
    private ParticipanteRepositorio participanteDB;

    public Optional<Participante> encontrarPorId(Long id){        
        return participanteDB.findById(id);
    }

    public List<ParticipanteDto> encontrar(){
        List<ParticipanteDto> listaDto = new ArrayList<ParticipanteDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Participante participante: participanteDB.findAll()) {
            listaDto.add(new ParticipanteDto(
                participante.getNomeArtistico(), participante.getNomeResponsavel(), participante.getGenero(), participante.getNascimento(),
                participante.getDocumentorg(), participante.getEmail(), participante.getNecessidade(), participante.getDescrinescessidade(), 
                participante.getEndereco(), participante.getCidade(), participante.getEstado(), 
                participante.getCep(), participante.getTelefone()));
        }

        return listaDto;
    }

    public boolean salvar(ParticipanteDto participante){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Participante p = new Participante(
                participante.getNomeArtistico(), participante.getNomeResponsavel(), participante.getGenero(), participante.getNascimento(),
                participante.getDocumentorg(), participante.getEmail(), participante.getNecessidade(), participante.getDescrinescessidade(), 
                participante.getEndereco(), participante.getCidade(), participante.getEstado(), 
                participante.getCep(), participante.getTelefone());

            this.participanteDB.save(p);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
