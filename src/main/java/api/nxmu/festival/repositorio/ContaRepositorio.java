package api.nxmu.festival.repositorio;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.nxmu.festival.usuario.Usuario;

public interface ContaRepositorio extends JpaRepository<Usuario, Integer>{
 
    Optional<Usuario> findByEmail(String email);
}
