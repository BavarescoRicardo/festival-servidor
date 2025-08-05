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
public class ImportacaoDto {

    public ImportacaoDto(
            String nomeArtistico, String nomeResponsavel, 
            String nascimento,
            String email, String telefone, 
            String necessidade, String descrinescessidade, 
            String nomeDupla, String nascimentoDupla, 
            String emailDupla, String telefoneDupla, 
            String nomeTrio, String emailTrio,
            String telefoneTrio, String nascimentoTrio, 
            String nomeGrupo, String emailGrupo, 
            String telefoneGrupo, String nascimentoGrupo, 
            Long apresentacao, String musica, 
            String tom, String gravacao, 
            String autor, String linkmusica,
            int individuos, Long categoria, 
            String categoriaTitulo, String endereco, 
            String cidade, String estado, 
            String cep, Long participante
        ) 
        {

        this.nomeArtistico = nomeArtistico;
        this.nomeResponsavel = nomeResponsavel;
        this.nascimento = nascimento;
        this.email = email;
        this.telefone = telefone;        
        this.necessidade = necessidade;
        this.descrinescessidade = descrinescessidade;
        this.nomeDupla = nomeDupla;
        this.nascimentoDupla = nascimentoDupla;
        this.emailDupla = emailDupla;
        this.telefoneDupla = telefoneDupla;
        this.nomeTrio = nomeTrio;
        this.emailTrio = emailTrio;
        this.telefoneTrio = telefoneTrio;
        this.nascimentoTrio = nascimentoTrio;
        this.nomeGrupo = nomeGrupo;
        this.emailGrupo = emailGrupo;
        this.telefoneGrupo = telefoneGrupo;
        this.nascimentoGrupo = nascimentoGrupo;
        this.apresentacao = apresentacao;
        this.musica = musica;
        this.tom = tom;
        this.gravacao = gravacao;
        this.autor = autor;
        this.linkmusica = linkmusica;
        this.individuos = individuos;
        this.categoria = categoria;
        this.categoriaTitulo = categoriaTitulo;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;        
        this.participante = participante;
    }
    private String nomeArtistico;
    private String nomeResponsavel;
    private String genero;
    private String nascimento;
    private String email;
    private String telefone;
    private String necessidade;
    private String descrinescessidade;

    //  // Repete para dupla
    private String nomeDupla; // Primeiro e ultimo nome
    private String nascimentoDupla;  // Data de nascimento
    private String emailDupla; // email
    private String telefoneDupla; // telefone
    //  // Repete para trio
    private String nomeTrio; // Primeiro e ultimo nome
    private String emailTrio;  // email
    private String telefoneTrio; // telefone
    private String nascimentoTrio; // Data de nascimento 
    //  // Repete pata grupo
    private String nomeGrupo; // Primeiro e ultimo nome
    private String emailGrupo;  // email
    private String telefoneGrupo; // telefone
    private String nascimentoGrupo; // Data de nascimento   

    private Long apresentacao;

    private String musica;
    private String tom;
    private String gravacao;
    private String autor;
    private String linkmusica;
    private int ordem;
    private int senha;
    private int individuos;
    private Long categoria;
    private String categoriaTitulo;  

    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private Long participante;

}
