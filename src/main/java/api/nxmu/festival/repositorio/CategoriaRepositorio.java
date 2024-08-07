package api.nxmu.festival.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import api.nxmu.festival.modelo.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{
 
    @Query("select u from Categoria u where u.ativo = 0")
    List<Categoria> findAllAtivos();	
}
