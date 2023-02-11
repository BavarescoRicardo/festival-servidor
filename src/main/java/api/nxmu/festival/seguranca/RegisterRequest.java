package api.nxmu.festival.seguranca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

      private String nomeArtistico;
      private String nomeResponsavel;
      private String genero;
      private String senha;
      private String nascimento;
      private String documentorg;
      private String email;
      private String necessidade;
      private String descrinescessidade;
      private String endereco;
      private String cidade;
      private String estado;
      private String cep;
      private String telefone;
      private String musica;
      private String gravacao;
      private String tom;
      private String categoria;
      private String participante;

}