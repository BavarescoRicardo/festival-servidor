package api.nxmu.festival.dto;

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
public class ParticipanteDto {
	private Long codigo;
    private String nomeArtistico;
    private String nomeResponsavel;
    private String genero;
    private String nascimento;
    private String documentorg;
    private String email;
    private String necessidade;
    private String descrinescessidade;
    private String cpf;
    private String pix;
    private String banco;
    private String agencia;
    private String conta;
    private Long apresentacao;
    private String senha;
    private byte[] fotoPerfil;
    
	public ParticipanteDto(Long codigo, String nomeArtistico, String nomeResponsavel, String genero, String nascimento,
			String documentorg, String email, String necessidade, String descrinescessidade, String cpf, String pix,
			String banco, String agencia, String conta, Long apresentacao, byte[] fotoPerfil) {
		this.codigo = codigo;
		this.nomeArtistico = nomeArtistico;
		this.nomeResponsavel = nomeResponsavel;
		this.genero = genero;
		this.nascimento = nascimento;
		this.documentorg = documentorg;
		this.email = email;
		this.necessidade = necessidade;
		this.descrinescessidade = descrinescessidade;
		this.cpf = cpf;
		this.pix = pix;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.apresentacao = apresentacao;
		this.fotoPerfil = fotoPerfil;
	}

	public ParticipanteDto(Long codigo, String nomeArtistico, String nomeResponsavel, String genero, String nascimento,
			String documentorg, String email, String necessidade, String descrinescessidade, String cpf, String pix,
			String banco, String agencia, String conta, Long apresentacao, String senha) {
		this.codigo = codigo;
		this.nomeArtistico = nomeArtistico;
		this.nomeResponsavel = nomeResponsavel;
		this.genero = genero;
		this.nascimento = nascimento;
		this.documentorg = documentorg;
		this.email = email;
		this.necessidade = necessidade;
		this.descrinescessidade = descrinescessidade;
		this.cpf = cpf;
		this.pix = pix;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.apresentacao = apresentacao;
		this.senha = senha;
	}   
    
}
