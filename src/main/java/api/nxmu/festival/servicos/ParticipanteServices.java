package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.modelo.Participante;
import api.nxmu.festival.repositorio.ParticipanteRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteServices {

    private final ParticipanteRepositorio participanteDB;

    public Optional<Participante> encontrarPorId(Long id){        
        return participanteDB.findById(id);
    }

    public List<ParticipanteDto> encontrar(){
        List<ParticipanteDto> listaDto = new ArrayList<ParticipanteDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Participante participante: participanteDB.findAll()) {
            listaDto.add(new ParticipanteDto(
                participante.getNomeArtistico(), participante.getNomeResponsavel(), participante.getGenero(), participante.getNascimento(),
                participante.getDocumentorg(), participante.getEmail(), participante.getNecessidade(), participante.getDescrinescessidade()));
        }

        return listaDto;
    }

    public Participante salvar(ParticipanteDto participante){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Participante p = new Participante(
                participante.getNomeArtistico(), participante.getNomeResponsavel(), participante.getGenero(), participante.getNascimento(),
                participante.getDocumentorg(), participante.getEmail(), participante.getNecessidade(), participante.getDescrinescessidade());

                return this.participanteDB.save(p);    
        } catch (Exception e) {
            return null;
        }
    }

    public ParticipanteDto atualizar(ParticipanteDto participante, long id){
        try {
            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Participante p = this.encontrarPorId(id).get();
            p.setNomeArtistico(participante.getNomeArtistico());
            p.setNomeResponsavel(participante.getNomeResponsavel()); 
            p.setGenero(participante.getGenero());
            p.setNascimento(participante.getNascimento());
            p.setDocumentorg(participante.getDocumentorg()); 
            p.setEmail(participante.getEmail());
            p.setNecessidade(participante.getNecessidade()); 
            p.setDescrinescessidade(participante.getDescrinescessidade());

            this.participanteDB.save(p);    
        } catch (Exception e) {
            return null;
        }
        return participante;
    }        
}
