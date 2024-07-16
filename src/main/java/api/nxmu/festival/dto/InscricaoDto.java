package api.nxmu.festival.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InscricaoDto {
    private ApresentacaoDto apresentacao;
    private Collection<ParticipanteDto> participantes;
    private EnderecoDto endereco;
    private String fotoDocumento;
}
