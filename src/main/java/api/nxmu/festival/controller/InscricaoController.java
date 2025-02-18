package api.nxmu.festival.controller;

import api.nxmu.festival.dto.InscricaoDto;
import api.nxmu.festival.servicos.ApresentacaoService;
import api.nxmu.festival.servicos.EnderecoService;
import api.nxmu.festival.servicos.InscricaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
