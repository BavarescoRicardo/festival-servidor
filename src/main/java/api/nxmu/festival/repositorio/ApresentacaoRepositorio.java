package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Apresentacao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApresentacaoRepositorio extends JpaRepository<Apresentacao, Long>{

    @Query("select u from Apresentacao u where u.categoria.id = ?1")
    List<Apresentacao> findAllByCategoria(Long codigoCategoria);

    @Query("select u from Apresentacao u where (u.categoria.id = ?1)")
    Page<Apresentacao> findAllFiltrado(long codCategoria, String textoFiltro, Pageable p);

    @Query("select u from Apresentacao u")
    Page<Apresentacao> findAllOrdenado(Pageable p);    
}
