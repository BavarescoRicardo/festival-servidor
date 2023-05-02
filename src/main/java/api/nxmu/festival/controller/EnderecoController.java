package api.nxmu.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.EnderecoDto;
import api.nxmu.festival.servicos.EnderecoServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class EnderecoController {

    @Autowired
    private final EnderecoServices enderecoService;

    @RequestMapping(value = "/enderecos", method =  RequestMethod.GET)
    public List<EnderecoDto> getEnderecos(){
        return enderecoService.encontrar();
    }    

    @RequestMapping(value = "/salvaendereco", method =  RequestMethod.POST)
	public boolean salvarEndereco(@RequestBody EnderecoDto endereco)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return enderecoService.salvar(endereco);
        } catch (Exception e) {
            return false;
        }               
	}

    @RequestMapping(value = "/atualizaendereco/{id}", method =  RequestMethod.PATCH)
	public EnderecoDto atualizarEndereco(@RequestBody EnderecoDto endereco, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return enderecoService.atualizar(endereco, id);
        } catch (Exception e) {
            return null;
        }               
	}    

}
