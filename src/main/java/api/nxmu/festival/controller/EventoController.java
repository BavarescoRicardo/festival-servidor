package api.nxmu.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.EventoDto;
import api.nxmu.festival.dto.RespostaRequestDto;
import api.nxmu.festival.servicos.EventoServices;
import api.nxmu.festival.utils.RespostaRequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class EventoController {

    private final EventoServices eventoService;

    @GetMapping(value = "/eventos")
    public ResponseEntity<?> getEventos() {
        try {
            return RespostaRequestUtils.buildResponseOk(eventoService.encontrar());
        } catch (Exception exception) {
            var mensagem = "Erro ao listar eventos";
            log.error(mensagem, exception);
            return RespostaRequestUtils.buildResponseInternalError(mensagem);
        }
    }    

    @PostMapping(value = "/salvaevento")
	public ResponseEntity<?> salvarEvento(@RequestBody EventoDto eventoDto) {
        try {
            var eventoSalvo = eventoService.salvar(eventoDto);
            return RespostaRequestUtils.buildResponseOk("Evento " +eventoSalvo.getTitulo() + " salvo!");
        } catch (Exception exception) {
            var mensagem = "Erro ao salvar evento " + eventoDto.getDescricao();
            log.error(mensagem, exception);
            return RespostaRequestUtils.buildResponseInternalError(mensagem);
        }
	}

    @PatchMapping(value = "/atualizaevento/{id}")
	public ResponseEntity<?> atualizarEvento(@RequestBody EventoDto eventoDto, @PathVariable long id) {
        try {
            var eventoAtualizado = eventoService.atualizar(eventoDto, id);
            return RespostaRequestUtils.buildResponseOk("Evento " +eventoAtualizado.getTitulo() + " atualizado!");
        } catch (Exception exception) {
            var mensagem = "Erro ao atualizar evento " + eventoDto.getDescricao();
            log.error(mensagem, exception);
            return RespostaRequestUtils.buildResponseInternalError(mensagem);
        }               
	}

    @DeleteMapping(value = "/removeevento/{id}")
	public ResponseEntity<?> removerEvento(@PathVariable long id) {
        try {
            eventoService.remover(id);
            return RespostaRequestUtils.buildResponseOk("Evento removido!");
        } catch (Exception exception) {
            var mensagem = "Erro ao atualizar evento ID" + id;
            log.error(mensagem, exception);
            return RespostaRequestUtils.buildResponseInternalError(mensagem);
        }
    }
}
