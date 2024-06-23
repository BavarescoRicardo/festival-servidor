package api.nxmu.festival.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApresentacaoDto {
    private Long codigo;
    private String musica;
    private String nomeartistico;
    private String tom;
    private String gravacao;
    private String autor;
    private String linkmusica;
    private int ordem;
    private int senha;
    private Long idEndereco;
    private String cidade;
    private Long categoria;
    private String categoriaTitulo;
    private String fotoDocumentoBase64;
}
