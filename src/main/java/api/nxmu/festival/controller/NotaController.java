package api.nxmu.festival.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.AtualizaNotaDto;
import api.nxmu.festival.dto.NotaDto;
import api.nxmu.festival.dto.NotaFinalDto;
import api.nxmu.festival.dto.TabelaNotaDto;
import api.nxmu.festival.dto.filtros.FiltroNotaDto;
import api.nxmu.festival.servicos.NotaServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class NotaController {

    @Autowired
    private final NotaServices notaService;

    @RequestMapping(value = "/notas", method =  RequestMethod.GET)
    public List<TabelaNotaDto> getNotas(){
        return notaService.encontrar();
    }
    
    @RequestMapping(value = "/notasfiltro", method =  RequestMethod.POST)
    public List<TabelaNotaDto> GetArtigoFiltrado(
    @RequestBody(required = false) Optional<FiltroNotaDto> filtro) {
        
        FiltroNotaDto filtroRecebido = filtro.get();
        return notaService.encontrarFiltrado(filtroRecebido);
    }      

    @RequestMapping(value = "/salvanota", method =  RequestMethod.POST)
	public boolean salvarNota(@RequestBody NotaDto nota)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return notaService.salvar(nota);
        } catch (Exception e) {
            return false;
        }               
	}

    @RequestMapping(value = "/salvanotas", method =  RequestMethod.POST)
	public boolean salvarNotas(@RequestBody List<NotaDto> notas)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            for (NotaDto notaDto : notas) {
                notaService.salvar(notaDto);    
            }
            return true;            
        } catch (Exception e) {
            return false;
        }               
	}    

    @RequestMapping(value = "/atualizanota/{id}", method =  RequestMethod.PATCH)
	public AtualizaNotaDto atualizarNota(@RequestBody AtualizaNotaDto nota, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return notaService.atualizar(nota, id);
        } catch (Exception e) {
            return null;
        }               
	}

    @RequestMapping(value = "/calcularnotafinal", method =  RequestMethod.POST)
	public boolean calcMedia(@RequestBody NotaFinalDto notaFinalDto)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            notaService.calcularNotaFinal(notaFinalDto.getApresentacao(), notaFinalDto.getJurado());
            return true;
        } catch (Exception e) {
            return false;
        }               
	}
    
    @RequestMapping(value = "/removenota/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removeNota(@PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return notaService.remover(id);
        } catch (Exception e) {
            return null;
        }
	}   

}
