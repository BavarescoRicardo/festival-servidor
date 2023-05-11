package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.EventoDto;
import api.nxmu.festival.modelo.Evento;
import api.nxmu.festival.repositorio.EventoRepositorio;

@Service
public class EventoServices {

    @Autowired
    private EventoRepositorio eventoDB;

    public Optional<Evento> encontrarPorId(Long id){        
        return eventoDB.findById(id);
    }    

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
                evento.getId(), evento.getTitulo(), evento.getDescricao(), 
                evento.getDataInicial(), evento.getDataFinal(), evento.getLocal()));
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

    public EventoDto atualizar(EventoDto evento, long id){
        try {
            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Evento e = this.encontrarPorId(id).get();
            e.setTitulo(evento.getTitulo());
            e.setDescricao(evento.getDescricao());
            e.setDataInicial(evento.getDataInicial());
            e.setDataFinal(evento.getDataFinal());
            e.setLocal(evento.getLocal());

            this.eventoDB.save(e);    
        } catch (Exception e) {
            return null;
        }
        return evento;
    }
    
    public ResponseEntity<String> remover(long id){
        try {
            
            Evento evento = this.encontrarPorId(id).get();
            this.eventoDB.delete(evento);

            return ResponseEntity.ok().body("Removido objeto de id: "+id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }    
}
