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

  // ajustar para modelo de inscricao -- isso eh um dto
  private String conta;
  private String nome;
  private String email;
  private String senha;
}