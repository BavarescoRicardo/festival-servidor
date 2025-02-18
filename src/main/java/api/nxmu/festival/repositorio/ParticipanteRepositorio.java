package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Participante;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParticipanteRepositorio extends JpaRepository<Participante, Long>{
 
	Participante findByEmail(String email);
	
    List<Participante> findByApresentacao(Long apresentacao);
}
