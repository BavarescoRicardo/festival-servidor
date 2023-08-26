package api.nxmu.festival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.ImportacaoEntradaDto;
import api.nxmu.festival.seguranca.AuthenticationRequest;
import api.nxmu.festival.seguranca.AuthenticationResponse;
import api.nxmu.festival.seguranca.AuthenticationService;
import api.nxmu.festival.seguranca.RegisterRequest;
import api.nxmu.festival.servicos.EnderecoServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/evento/auth")
@RequiredArgsConstructor
@CrossOrigin
public class ContaController {

    @Autowired
    private final AuthenticationService service;
    
    @Autowired
    private final EnderecoServices enderecoServices;

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
    

    @RequestMapping(value = "/testaapi", method =  RequestMethod.POST)
	public ResponseEntity<?> testeApi(
    @RequestBody ImportacaoEntradaDto arquivo)
    {
        //  envolver metodo em try catch retorno certo no tr retorno erraado no false
        try {
            enderecoServices.importar(arquivo.getSes());
            return ResponseEntity.ok().body("Executadas açoes com sucesso absoluto!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar role no banco de dados  " + e.getMessage());
        }               
	}

    @RequestMapping(value = "/removeconta/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removerConta(@PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return service.remover(id);
        } catch (Exception e) {
            return null;
        }
	}

}
