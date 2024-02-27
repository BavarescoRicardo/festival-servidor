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

    public Evento salvar(EventoDto eventoDto) {
        Evento evento = new Evento(eventoDto.getTitulo(), eventoDto.getDescricao(), eventoDto.getDataInicial(), eventoDto.getDataFinal(), eventoDto.getLocal());
        return this.eventoDB.save(evento);
    }

    public Evento atualizar(EventoDto eventoDto, long id) {
        Evento evento = this.encontrarPorId(id).orElseThrow(() -> new RuntimeException("Evento não encontrado!"));
        evento.setTitulo(eventoDto.getTitulo());
        evento.setDescricao(eventoDto.getDescricao());
        evento.setDataInicial(eventoDto.getDataInicial());
        evento.setDataFinal(eventoDto.getDataFinal());
        evento.setLocal(eventoDto.getLocal());

        return this.eventoDB.save(evento);
    }
    
    public void remover(long id){
        Evento evento = this.encontrarPorId(id).orElseThrow(() -> new RuntimeException("Evento não encontrado!"));
        this.eventoDB.delete(evento);
    }
}
