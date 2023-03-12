package api.nxmu.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.EventoDto;
import api.nxmu.festival.servicos.EventoServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/evento/")
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
	public boolean salvarEvento(@RequestBody EventoDto participante)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return eventoService.salvar(participante);
        } catch (Exception e) {
            return false;
        }               
	}

}
