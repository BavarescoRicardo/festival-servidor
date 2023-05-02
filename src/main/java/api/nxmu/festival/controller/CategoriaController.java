package api.nxmu.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.CategoriaDto;
import api.nxmu.festival.servicos.CategoriaServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class CategoriaController {

    @Autowired
    private final CategoriaServices categoriaService;

    @RequestMapping(value = "/categorias", method =  RequestMethod.GET)
    public List<CategoriaDto> getCategorias(){
        return categoriaService.encontrar();
    }    

    @RequestMapping(value = "/salvacategoria", method =  RequestMethod.POST)
	public boolean salvarCategoria(@RequestBody CategoriaDto categoria)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return categoriaService.salvar(categoria);
        } catch (Exception e) {
            return false;
        }               
	}

    @RequestMapping(value = "/atualizacategoria/{id}", method =  RequestMethod.PATCH)
	public CategoriaDto atualizarCategoria(@RequestBody CategoriaDto categoria, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return categoriaService.atualizar(categoria, id);
        } catch (Exception e) {
            return null;
        }               
	}
}
