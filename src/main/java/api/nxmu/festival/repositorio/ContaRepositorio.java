package api.nxmu.festival.repositorio;
import api.nxmu.festival.modelo.Conta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepositorio extends JpaRepository<Conta, Integer>{
 
    Optional<Conta> findByEmail(String email);
}
