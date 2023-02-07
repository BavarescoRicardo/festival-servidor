package api.nxmu.festival.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;

@RestController
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

}
