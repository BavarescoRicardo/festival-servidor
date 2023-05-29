package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Classificacao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassificacaoRepositorio extends JpaRepository<Classificacao, Long>{

    // Must be One
    @Query("select u from Classificacao u where u.apresentacao.id = ?1")
    Optional<Classificacao> findByIdApresentacao(long codigo);

    @Query("select u from Classificacao u where (u.categoria.id = ?1) AND ((u.apresentacao.musica like  '%' || ?2 || '%' ) OR (u.apresentacao.nomeartistico like  '%' || ?2 || '%' ))")
    Page<Classificacao> findAllFiltrado(long codCategoria, String textoFiltro, Pageable p);    
}
