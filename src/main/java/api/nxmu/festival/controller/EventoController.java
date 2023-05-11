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

import api.nxmu.festival.dto.EventoDto;
import api.nxmu.festival.servicos.EventoServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class EventoController {

    @Autowired
    private final EventoServices eventoService;

    @RequestMapping(value = "/eventos", method =  RequestMethod.GET)
    public List<EventoDto> getEventos(){
        return eventoService.encontrar();
    }    

    @RequestMapping(value = "/salvaevento", method =  RequestMethod.POST)
	public boolean salvarEvento(@RequestBody EventoDto evento)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return eventoService.salvar(evento);
        } catch (Exception e) {
            return false;
        }               
	}

    @RequestMapping(value = "/atualizaevento/{id}", method =  RequestMethod.PATCH)
	public EventoDto atualizarEvento(@RequestBody EventoDto evento, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return eventoService.atualizar(evento, id);
        } catch (Exception e) {
            return null;
        }               
	}

    @RequestMapping(value = "/removeevento/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removerEvento(@PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return eventoService.remover(id);
        } catch (Exception e) {
            return null;
        }
	}     
}
