package api.nxmu.festival.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/evento")
@RequiredArgsConstructor
public class UsuarioController {

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

    // @PostMapping("/register")
    // public ResponseEntity<AuthenticationResponse> register (
    //     @RequestBody RegisterRequest request        
    // ){

    // }

    // @PostMapping("/authenticate")
    // public ResponseEntity<AuthenticationResponse> register (
    //     @RequestBody AuthenticateRequest request        
    // ){

    // }

}
