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

import api.nxmu.festival.dto.CalculoClassificaoDto;
import api.nxmu.festival.dto.ClassificacaoDto;
import api.nxmu.festival.dto.ClassificacaoListaDto;
import api.nxmu.festival.servicos.ClassificacaoServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class ClassificacaoController {

    @Autowired
    private final ClassificacaoServices classificacaoService;

    @RequestMapping(value = "/classificacoes", method =  RequestMethod.GET)
    public List<ClassificacaoListaDto> getClassificacoes(){
        return classificacaoService.encontrar();
    }    

    @RequestMapping(value = "/salvaclassificacao", method =  RequestMethod.POST)
	public boolean salvarClassif(@RequestBody ClassificacaoDto classificacao)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return classificacaoService.salvar(classificacao);
        } catch (Exception e) {
            return false;
        }               
	}

    @RequestMapping(value = "/atualizaclassificacao/{id}", method =  RequestMethod.PATCH)
	public ClassificacaoDto atualizarClassif(@RequestBody ClassificacaoDto classificacao, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return classificacaoService.atualizar(classificacao, id);
        } catch (Exception e) {
            return null;
        }               
	}

    @RequestMapping(value = "/removeclassificacao/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removerClassif(@PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return classificacaoService.remover(id);
        } catch (Exception e) {
            return null;
        }               
	}    

    @RequestMapping(value = "/calcularclassificacao", method =  RequestMethod.POST)
	public boolean calcMedia(@RequestBody CalculoClassificaoDto calculoClassificaoDto)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            classificacaoService.calcularClassificacao(calculoClassificaoDto.getCategoria());
            return true;
        } catch (Exception e) {
            throw e;
        }               
	}

}
