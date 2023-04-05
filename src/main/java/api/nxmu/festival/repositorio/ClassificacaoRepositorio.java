package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Classificacao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassificacaoRepositorio extends JpaRepository<Classificacao, Long>{

    // Must be One
    @Query("select u from Classificacao u where u.apresentacao = ?1")
    Optional<Classificacao> findByIdApresentacao(Long codigo);
}
