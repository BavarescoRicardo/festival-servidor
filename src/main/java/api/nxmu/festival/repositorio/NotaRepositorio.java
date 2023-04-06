package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Nota;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotaRepositorio extends JpaRepository<Nota, Long>{ 

    @Query("select u from Nota u where u.apresentacao.id = ?1 and u.jurado.id = ?2")
    List<Nota> findAllByApresentacaoJurado(Long codigoApresentacao, Long codigoJurado);
}
