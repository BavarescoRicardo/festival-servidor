package api.nxmu.festival.repositorio;
import api.nxmu.festival.usuario.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
 
    Optional<Usuario> findByEmail(String email);
}
