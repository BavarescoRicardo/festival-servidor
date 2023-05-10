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

import api.nxmu.festival.dto.ApresentacaoDto;
import api.nxmu.festival.servicos.ApresentacaoServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class ApresentacaoController {

    @Autowired
    private final ApresentacaoServices apresentacaoService;

    @RequestMapping(value = "/apresentacoes", method =  RequestMethod.GET)
    public List<ApresentacaoDto> getApresentacoes(){
        return apresentacaoService.encontrar();
    }    

    @RequestMapping(value = "/salvaapresentacao", method =  RequestMethod.POST)
	public boolean salvarApresentacao(@RequestBody ApresentacaoDto apresentacao)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return apresentacaoService.salvar(apresentacao);
        } catch (Exception e) {
            return false;
        }               
	}

    @RequestMapping(value = "/apresentacao/{id}", method =  RequestMethod.PATCH)
	public ApresentacaoDto atualizarApresentacao(@RequestBody ApresentacaoDto apresentacao, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return apresentacaoService.atualizar(apresentacao, id);
        } catch (Exception e) {
            return null;
        }               
	}
    
    @RequestMapping(value = "/removeapresentacao/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removeApresentacao(@PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return apresentacaoService.remover(id);
        } catch (Exception e) {
            return null;
        }
	}    

}
