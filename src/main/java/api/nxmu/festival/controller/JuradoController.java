package api.nxmu.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    private final JuradoServices eventoService;

    @RequestMapping(value = "/jurados", method =  RequestMethod.GET)
    public List<JuradoDto> getEventos(){
        return eventoService.encontrar();
    }    

    @RequestMapping(value = "/salvajurado", method =  RequestMethod.POST)
	public boolean salvarEvento(@RequestBody JuradoDto evento)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return eventoService.salvar(evento);
        } catch (Exception e) {
            return false;
        }               
	}

}
