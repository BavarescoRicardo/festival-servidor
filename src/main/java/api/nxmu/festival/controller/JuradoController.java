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

import api.nxmu.festival.dto.JuradoDto;
import api.nxmu.festival.servicos.JuradoServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class JuradoController {

    @Autowired
    private final JuradoServices juradoService;

    @RequestMapping(value = "/jurados", method =  RequestMethod.GET)
    public List<JuradoDto> getJurados(){
        return juradoService.encontrar();
    }    

    @RequestMapping(value = "/salvajurado", method =  RequestMethod.POST)
	public boolean salvarJurado(@RequestBody JuradoDto jurado)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return juradoService.salvar(jurado);
        } catch (Exception e) {
            return false;
        }          
	}

    @RequestMapping(value = "/atualizajurado/{id}", method =  RequestMethod.PATCH)
	public JuradoDto atualizarJurado(@RequestBody JuradoDto jurado, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return juradoService.atualizar(jurado, id);
        } catch (Exception e) {
            return null;
        }               
	}

    @RequestMapping(value = "/removejurado/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removerJurado(@PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return juradoService.remover(id);
        } catch (Exception e) {
            return null;
        }
	}   
}
