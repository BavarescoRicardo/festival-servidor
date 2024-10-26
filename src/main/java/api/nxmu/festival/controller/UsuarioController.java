package api.nxmu.festival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import api.nxmu.festival.dto.ImportacaoEntradaDto;
import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.modelo.Usuario;
import api.nxmu.festival.seguranca.AuthenticationRequest;
import api.nxmu.festival.seguranca.AuthenticationResponse;
import api.nxmu.festival.seguranca.AuthenticationService;
import api.nxmu.festival.seguranca.RegisterRequest;
import api.nxmu.festival.servicos.EnderecoService;
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
	public ParticipanteDto selecionaUsuario(Authentication auth)
    {
        try {
            return usuarioService.selecionaParticipanteAtual(auth);
        } catch (Exception e) {
            return null;
        }               
	}    

    @GetMapping("/testaapi")
	public ResponseEntity<?> testeApi()
    {
        try {
            return ResponseEntity.ok().body("Executadas açoes com sucesso absoluto!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar role no banco de dados  " + e.getMessage());
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
