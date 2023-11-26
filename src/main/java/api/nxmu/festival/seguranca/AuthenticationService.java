package api.nxmu.festival.seguranca;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.nxmu.festival.repositorio.UsuarioRepositorio;
import api.nxmu.festival.usuario.Usuario;
import api.nxmu.festival.usuario.Role;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UsuarioRepositorio repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = Usuario.builder()
        .email(request.getEmail())        
        .senha(passwordEncoder.encode(request.getSenha()))
        .role(Role.MASTER)
        .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getSenha()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public ResponseEntity<String> remover(long id){
    try {
        
        Usuario user = this.repository.findById(id).get();
        this.repository.delete(user);            

        return ResponseEntity.ok().body("Removido objeto de id: "+id);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
}  
}

