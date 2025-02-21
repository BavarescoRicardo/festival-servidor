package api.nxmu.festival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.PerfilDto;
import api.nxmu.festival.seguranca.AuthenticationRequest;
import api.nxmu.festival.seguranca.AuthenticationResponse;
import api.nxmu.festival.seguranca.AuthenticationService;
import api.nxmu.festival.seguranca.RegisterRequest;
import api.nxmu.festival.servicos.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/evento/auth")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {

    @Autowired
    private final AuthenticationService service;
    
    @Autowired
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request
    ) {
      return ResponseEntity.ok(service.register(request));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthenticationRequest request
    ) {
      return ResponseEntity.ok(service.authenticate(request));
    }

    @RequestMapping(value = "/selusuario", method =  RequestMethod.GET)
	public PerfilDto selecionaUsuario(Authentication auth)
    {
        try {
            return usuarioService.selecionaPerfil(auth);
        } catch (Exception e) {
            return null;
        }               
	}    

    @PostMapping(value = "/perfil")
	public PerfilDto atualizarPerfil(PerfilDto pefilDto)
    {
        try {
            return usuarioService.atualizaPerfil(pefilDto);
        } catch (Exception e) {
            return null;
        }               
	}    

    @RequestMapping(value = "/removeconta/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removerConta(@PathVariable long id)
    {
        try {
            return service.remover(id);
        } catch (Exception e) {
            return null;
        }
	}

}
