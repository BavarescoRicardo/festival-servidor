package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Apresentacao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApresentacaoRepositorio extends JpaRepository<Apresentacao, Long>{

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

    @Query("select new map(a.id as codigo, a.musica as musica, a.nomeartistico as nomeartistico, a.autor as autor, " +
            "a.ordem as ordem, c.titulo as categoriaTitulo) " +
            "from Apresentacao a " +
            "join a.categoria c")
    Page<Map<String, Object>> findAllCartao(Pageable p);
}
