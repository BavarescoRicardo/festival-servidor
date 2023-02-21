package api.nxmu.festival.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.modelo.Participante;
import api.nxmu.festival.seguranca.AuthenticationRequest;
import api.nxmu.festival.seguranca.AuthenticationResponse;
import api.nxmu.festival.seguranca.AuthenticationService;
import api.nxmu.festival.seguranca.RegisterRequest;
import api.nxmu.festival.servicos.ParticipanteServices;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/evento/auth")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {

    @Autowired
    private final AuthenticationService service;
    @Autowired
    private final ParticipanteServices participanteService;

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
    

    @RequestMapping(value = "/testaapi", method =  RequestMethod.GET)
	public ResponseEntity<?> testeApi()
    {
        //  envolver metodo em try catch retorno certo no tr retorno erraado no false
        try {
            return ResponseEntity.ok().body("Executadas açoes com sucesso absoluto!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar role no banco de dados  " + e.getMessage());
        }               
	}

    @RequestMapping(value = "/participantes", method =  RequestMethod.GET)
    public List<ParticipanteDto> getParticipantes(){
        return participanteService.encontrar();
    }    

    @RequestMapping(value = "/salvaparticipante", method =  RequestMethod.POST)
	public boolean salvarParticipante(@RequestBody ParticipanteDto participante)
    {
        //  envolver metodo em try catch retorno certo no tr retorno erraado no false
        try {
            return participanteService.salvar(participante);
        } catch (Exception e) {
            return false;
        }               
	}

}
