package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.NotaFinal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotaFinalRepositorio extends JpaRepository<NotaFinal, Long>{ 

    @Query("select u from NotaFinal u where u.apresentacao.id = ?1")
    List<NotaFinal> findAllByApresentacao(Long codigoApresentacao); 
    
    @Query("select u from NotaFinal u where u.apresentacao.id = ?1 and u.jurado.id = ?2")
    List<NotaFinal> findAllByApresentacaoJurado(Long codigoApresentacao, Long codigoJurado);
    
    @Query("select u from NotaFinal u where u.categoria.id = ?1")
    List<NotaFinal> findAllByCategoria(Long codigoCategoria);     
}
