package api.nxmu.festival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.CategoriaDto;
import api.nxmu.festival.dto.RespostaErrorDto;
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
    public ResponseEntity<?> getCategorias(){        
        try {
            return ResponseEntity.ok().body(categoriaService.encontrar());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
            		RespostaErrorDto.builder().mensagem("Não foi possível salvar " + e.getMessage()));
        }         
    }    

    @RequestMapping(value = "/salvacategoria", method =  RequestMethod.POST)
	public ResponseEntity<?> salvarCategoria(@RequestBody CategoriaDto categoria)
    {
        try {
            return ResponseEntity.ok().body(categoriaService.salvar(categoria));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
            		RespostaErrorDto.builder().mensagem("Não foi possível salvar " + e.getMessage()));
        }               
	}

    @RequestMapping(value = "/atualizacategoria/{id}", method =  RequestMethod.PATCH)
	public ResponseEntity<?> atualizarCategoria(@RequestBody CategoriaDto categoria, @PathVariable long id)
    {
        try {
            return ResponseEntity.ok().body(categoriaService.atualizar(categoria, id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }          
	}

    @RequestMapping(value = "/removecategoria/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<?> removeCategoria(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok().body("Removido objeto de id: "+categoriaService.remover(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
        
}
