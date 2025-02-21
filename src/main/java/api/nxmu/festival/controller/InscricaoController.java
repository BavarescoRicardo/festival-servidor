package api.nxmu.festival.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.InscricaoDto;
import api.nxmu.festival.servicos.InscricaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inscricoes")
@RequiredArgsConstructor
@CrossOrigin
public class InscricaoController {

    private final InscricaoService inscricaoService;

    @PostMapping()
    public ResponseEntity<String> salvarInscricao(@RequestBody InscricaoDto inscricaoDto) {
        try {
            inscricaoService.salvarInscricao(inscricaoDto);
            return ResponseEntity.ok("Ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
