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

    @Query("select u from Classificacao u where (u.apresentacao.categoria.id = ?1) order by notafinal desc")
    Page<Classificacao> findAllFiltrado(long codCategoria, String textoFiltro, Pageable p);    
    
    @Query("select u from Classificacao u where (u.apresentacao.categoria.id = ?1) order by u.apresentacao.nomeartistico")
    Page<Classificacao> findAllFiltradoAlfabetico(long codCategoria, String textoFiltro, Pageable p);    
}
