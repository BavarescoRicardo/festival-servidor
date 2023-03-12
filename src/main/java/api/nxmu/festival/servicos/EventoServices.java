package api.nxmu.festival.servicos;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.EventoDto;
import api.nxmu.festival.modelo.Evento;
import api.nxmu.festival.repositorio.EventoRepositorio;

@Service
public class EventoServices {

    @Autowired
    private EventoRepositorio eventoDB;

    // public void salvarFotoForm(MultipartFile img, Authentication auth) {
    //     try {
    //         UserDetails userd = (UserDetails)auth.getPrincipal();
    //         Participante participante = participanteDB.findByNomeLogin(userd.getUsername());
            
    //         if(participante == null){
    //             throw new Exception("Usuario não encontrado");
    //         }
    //         participanteDB.setFotoPerfil(img.getBytes());
    //         this.participanteDB.save(participante);    
    //     } catch (Exception e) {
    //         return;
    //     }
    // }

    public List<EventoDto> encontrar(){
        List<EventoDto> listaDto = new ArrayList<EventoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Evento evento: eventoDB.findAll()) {
            listaDto.add(new EventoDto(
                evento.getTitulo(), evento.getDescricao(), 
                evento.getDataInicial(), evento.getDataFinal()));
        }

        return listaDto;
    }

    public boolean salvar(EventoDto evento){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Evento e = new Evento(
                evento.getTitulo(), evento.getDescricao(), 
                evento.getDataInicial(), evento.getDataFinal(), evento.getLocal());

            this.eventoDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
