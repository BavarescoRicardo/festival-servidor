package api.nxmu.festival.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.nxmu.festival.modelo.Participante;

public interface ParticipanteRepositorio extends JpaRepository<Participante, Long>{
 
	Participante findByEmail(String email);
	
    List<Participante> findByApresentacao(Long apresentacao);
}
