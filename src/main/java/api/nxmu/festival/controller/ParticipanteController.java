package api.nxmu.festival.controller;

import java.io.IOException;
import java.util.List;

import api.nxmu.festival.servicos.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.nxmu.festival.dto.ParticipanteDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/evento/")
@RequiredArgsConstructor
@CrossOrigin
public class ParticipanteController {

    @Autowired
    private final ParticipanteService participanteService;

    @RequestMapping(value = "/participantes", method =  RequestMethod.GET)
    public List<ParticipanteDto> getParticipantes(){
        return participanteService.encontrar();
    }

    @RequestMapping(value = "/salvaparticipante", method = RequestMethod.POST)
    public ResponseEntity<Long> salvarParticipante(@RequestBody ParticipanteDto participante) {
        try {
            return ResponseEntity.ok(participanteService.salvar(participante));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/atualizaparticipante/{id}", method =  RequestMethod.PATCH)
	public ParticipanteDto atualizarParticipante(@RequestBody ParticipanteDto participante, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return participanteService.atualizar(participante, id);
        } catch (Exception e) {
            return null;
        }               
	}

    @RequestMapping(value = "/removeparticipante/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removeParticipante(@PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return participanteService.remover(id);
        } catch (Exception e) {
            return null;
        }
	}
    
    @PostMapping("/participante/foto")
    public ResponseEntity<Object> savaImagem(int participante, MultipartFile image) throws IOException {
        try {
            Long idA =  (long) participante;
            this.participanteService.savaImagem(idA, image);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro controller. Não foi possível salvar imagem");
        }

        return ResponseEntity.ok("Imagem salva com sucesso");     
    }
    
    @PostMapping("/participante/documento")
    public ResponseEntity<Object> savaDocumento(int participante, MultipartFile image) throws IOException {
        try {
            Long idParticipante =  (long) participante;
            this.participanteService.savaDocumento(idParticipante, image);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro controller. Não foi possível salvar imagem");
        }

        return ResponseEntity.ok("Imagem salva com sucesso");     
    }  
    
}
