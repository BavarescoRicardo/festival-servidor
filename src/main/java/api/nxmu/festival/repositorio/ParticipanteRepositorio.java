package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Participante;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepositorio extends JpaRepository<Participante, Long>{
 
    Optional<Participante> findByEmail(String email);
}
