package api.nxmu.festival.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PerfilDto {
    private Long codigo;
    private String nomeArtistico;
    private String nomeResponsavel;
    private String genero;
    private String nascimento;
    private String email;
    private String necessidade;
    private String descrinescessidade;
    private String cpf;
    private String pix;
    private String banco;
    private String agencia;
    private String conta;
    private String apresentacao;
    private String endereco;
    private byte[] fotoPerfil;
    private String status;             
    private String classificacao;    
    private Integer progressoPerfil;   

	public PerfilDto(Long codigo, String nomeArtistico, String nomeResponsavel, String genero, String nascimento,
			String email, String necessidade, String descrinescessidade, String cpf, String pix,
			String banco, String agencia, String conta, String apresentacao, String endereco) {
		this.codigo = codigo;
		this.nomeArtistico = nomeArtistico;
		this.nomeResponsavel = nomeResponsavel;
		this.genero = genero;
		this.nascimento = nascimento;
		this.email = email;
		this.necessidade = necessidade;
		this.descrinescessidade = descrinescessidade;
		this.cpf = cpf;
		this.pix = pix;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.apresentacao = apresentacao;
		this.endereco = endereco;
	}
    
}
