package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Nota;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotaRepositorio extends JpaRepository<Nota, Long>{ 

    @Query("select u from Nota u where u.apresentacao.id = ?1 and u.jurado.id = ?2")
    List<Nota> findAllByApresentacaoJurado(Long codigoApresentacao, Long codigoJurado);

    @Query("select u from Nota u where u.apresentacao.id = ?1 and u.jurado.id = ?2 and u.quesito.id = ?3")
    List<Nota> encontrarPorApresentacaoeJuradoQuesito(Long codigoApresentacao, Long codigoJurado, long codigoQuesito);    

    @Query("select u from Nota u where (u.categoria.id = ?1)")
    Page<Nota> findAllFiltrado(long codCategoria, Pageable p);    
}
