package api.nxmu.festival.repositorio;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.nxmu.festival.modelo.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long>{

    // Must be One
    @Query("select u from Endereco u where u.participante.id = ?1")
    Optional<Endereco> findByIdParticipante(Long codigo);
    
    @Query("delete from Endereco where participante.id = ?1")
    void removeByIdParticipante(Long codigo);    
}
