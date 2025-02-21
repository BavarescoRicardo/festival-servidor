package api.nxmu.festival.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import api.nxmu.festival.modelo.Evento;

public interface EventoRepositorio extends JpaRepository<Evento, Long>{
 
}
