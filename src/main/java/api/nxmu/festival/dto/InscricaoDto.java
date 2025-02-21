package api.nxmu.festival.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InscricaoDto {
    private ApresentacaoDto apresentacao;
    private Collection<ParticipanteDto> participantes;
    private EnderecoDto endereco;
    private String fotoDocumento;
    private String fotoTermo;
}
