package api.nxmu.festival.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.nxmu.festival.dto.MusicaDto;
import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.projections.ApresentacaoProjection;

public interface ApresentacaoRepositorio extends JpaRepository<Apresentacao, Long> {

    @Query("select u from Apresentacao u where u.ativo = 0 and u.categoria.id = ?1")
    List<Apresentacao> findAllByCategoria(Long codigoCategoria);
    
    @Query("select u from Apresentacao u join u.participantes p where u.ativo = 0 and p.id = ?1")
    List<Apresentacao> findAllByParticipante(Long codigoParticipante);    

    @Query("select u from Apresentacao u where u.ativo = 0 and (u.categoria.id = ?1)")
    Page<Apresentacao> findAllFiltrado(long codCategoria, String textoFiltro, Pageable p);

    @Query("select u from Apresentacao u where u.ativo = 0 and (u.categoria.id = ?1) order by nomeartistico")
    Page<Apresentacao> findAllClassificacao(long codCategoria, String textoFiltro, Pageable p);

    @Query("select u from Apresentacao u where u.ativo = 0 and u.senha > 0 and u.categoria.id = ?1")
    Page<Apresentacao> findAllFiltradoEnsaio(long codCategoria, String textoFiltro, Pageable p);

    @Query("select u from Apresentacao u where u.ativo = 0")
    Page<Apresentacao> findAllOrdenado(Pageable p);
    
    @Query("SELECT new api.nxmu.festival.dto.MusicaDto(u.id, u.musica, u.autor, u.linkmusica, u.categoria.id) " +
    	       "FROM Apresentacao u " +
    	       "WHERE u.ativo = 0 AND LOWER(u.musica) = LOWER(?1) AND u.categoria.id = ?2")
	List<MusicaDto> findByCategoriaAndMusica(String musica, Long codigoCategoria);

    

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
