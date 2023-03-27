package api.nxmu.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.NotaDto;
import api.nxmu.festival.dto.TabelaNotaDto;
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
    public List<TabelaNotaDto> getEventos(){
        return notaService.encontrar();
    }    

    @RequestMapping(value = "/salvanota", method =  RequestMethod.POST)
	public boolean salvarEvento(@RequestBody NotaDto evento)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return notaService.salvar(evento);
        } catch (Exception e) {
            return false;
        }               
	}

}
