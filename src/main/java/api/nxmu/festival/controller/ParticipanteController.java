package api.nxmu.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.servicos.ParticipanteServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/evento/")
@RequiredArgsConstructor
@CrossOrigin
public class ParticipanteController {

    @Autowired
    private final ParticipanteServices participanteService;

    @RequestMapping(value = "/participantes", method =  RequestMethod.GET)
    public List<ParticipanteDto> getParticipantes(){
        return participanteService.encontrar();
    }    

    @RequestMapping(value = "/salvaparticipante", method =  RequestMethod.POST)
	public boolean salvarParticipante(@RequestBody ParticipanteDto participante)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return participanteService.salvar(participante);
        } catch (Exception e) {
            return false;
        }               
	}

}
