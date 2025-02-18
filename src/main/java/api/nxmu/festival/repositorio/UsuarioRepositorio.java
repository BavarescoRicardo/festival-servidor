package api.nxmu.festival.repositorio;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.nxmu.festival.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
 
    Optional<Usuario> findByEmail(String email);
}
