package api.nxmu.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.QuesitoDto;
import api.nxmu.festival.servicos.QuesitoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class QuesitoController {

    @Autowired
    private final QuesitoService quesitoService;

    @RequestMapping(value = "/quesitos", method =  RequestMethod.GET)
    public List<QuesitoDto> getQuesitos(){
        return quesitoService.encontrar();
    }    

    @RequestMapping(value = "/salvaquesito", method =  RequestMethod.POST)
	public boolean salvarQuesito(@RequestBody QuesitoDto quesito)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return quesitoService.salvar(quesito);
        } catch (Exception e) {
            return false;
        }               
	}

    @RequestMapping(value = "/atualizaquesito/{id}", method =  RequestMethod.PATCH)
	public QuesitoDto atualizarQuesito(@RequestBody QuesitoDto quesito, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return quesitoService.atualizar(quesito, id);
        } catch (Exception e) {
            return null;
        }              
	}

    @RequestMapping(value = "/removequesito/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removeQuesito(@PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return quesitoService.remover(id);
        } catch (Exception e) {
            return null;
        }
	}    
}
