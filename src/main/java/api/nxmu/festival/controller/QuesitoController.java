package api.nxmu.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.QuesitoDto;
import api.nxmu.festival.servicos.QuesitoServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class QuesitoController {

    @Autowired
    private final QuesitoServices eventoService;

    @RequestMapping(value = "/quesitos", method =  RequestMethod.GET)
    public List<QuesitoDto> getEventos(){
        return eventoService.encontrar();
    }    

    @RequestMapping(value = "/salvaquesito", method =  RequestMethod.POST)
	public boolean salvarEvento(@RequestBody QuesitoDto evento)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return eventoService.salvar(evento);
        } catch (Exception e) {
            return false;
        }               
	}

}
