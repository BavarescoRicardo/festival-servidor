package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Endereco;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long>{

    // Must be One
    @Query("select u from Endereco u where u.participante = ?1")
    Optional<Endereco> findByIdParticipante(Long codigo);
}
