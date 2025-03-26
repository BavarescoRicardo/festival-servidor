package api.nxmu.festival.repositorio;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.modelo.Participante;

public interface ParticipanteRepositorio extends JpaRepository<Participante, Long>{
 
	Participante findByEmail(String email);
	
    List<Participante> findByApresentacao(Long apresentacao);
    
    @Query("select u from Participante u where u.apresentacao.categoria.id = ?1")
    Page<Participante> findAllFiltrado(long codCategoria, Pageable p);    
}
