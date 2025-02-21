package api.nxmu.festival.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.projections.ApresentacaoProjection;

public interface ApresentacaoRepositorio extends JpaRepository<Apresentacao, Long> {

    @Query("select u from Apresentacao u where u.ativo = 0 and u.categoria.id = ?1")
    List<Apresentacao> findAllByCategoria(Long codigoCategoria);

    @Query("select u from Apresentacao u where u.ativo = 0 and (u.categoria.id = ?1)")
    Page<Apresentacao> findAllFiltrado(long codCategoria, String textoFiltro, Pageable p);

    @Query("select u from Apresentacao u where u.ativo = 0 and (u.categoria.id = ?1) order by nomeartistico")
    Page<Apresentacao> findAllClassificacao(long codCategoria, String textoFiltro, Pageable p);

    @Query("select u from Apresentacao u where u.ativo = 0 and u.senha > 0 and u.categoria.id = ?1")
    Page<Apresentacao> findAllFiltradoEnsaio(long codCategoria, String textoFiltro, Pageable p);

    @Query("select u from Apresentacao u")
    Page<Apresentacao> findAllOrdenado(Pageable p);

    @Query(""" 
            select 
                a.id as codigo,
                a.musica as musica,
                a.nomeartistico as nomeArtistico,
                a.autor as nomeAutor,
                a.ordem as ordem,
                c.titulo as categoriaTitulo
            from Apresentacao a
                join a.categoria c
            """)
    List<ApresentacaoProjection> findAllCartao(Pageable p);
}
