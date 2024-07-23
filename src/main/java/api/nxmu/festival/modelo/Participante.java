package api.nxmu.festival.modelo;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeArtistico;
    private String nomeResponsavel;
    private String genero;
    private String nascimento;
    private String documentorg;
    private String email;
    private String necessidade;
    private String descrinescessidade;
    private String pix;
    private String agencia;
    private String conta;
    private int ativo;
    private byte[] fotoPerfil;
    private byte[] fotoDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apresentacao_id")
    private Apresentacao apresentacao;    

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "participante")
    private List<Endereco> enderecos;

    public Participante(String nomeArtistico, String nomeResponsavel, String genero, String nascimento,
            String documentorg, String email, String necessidade, String descrinescessidade, String pix, 
            String agencia, String conta, Apresentacao apresentacao) {
        this.nomeArtistico = nomeArtistico;
        this.nomeResponsavel = nomeResponsavel;
        this.genero = genero;
        this.nascimento = nascimento;
        this.documentorg = documentorg;
        this.email = email;
        this.necessidade = necessidade;
        this.descrinescessidade = descrinescessidade;
        this.pix = pix;
        this.agencia = agencia;
        this.conta = conta;
        this.apresentacao = apresentacao;
    }    

    public Participante() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomeArtistico() {
        return nomeArtistico;
    }
    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getNascimento() {
        return nascimento;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    public String getDocumentorg() {
        return documentorg;
    }
    public void setDocumentorg(String documentorg) {
        this.documentorg = documentorg;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNecessidade() {
        return necessidade;
    }
    public void setNecessidade(String necessidade) {
        this.necessidade = necessidade;
    }
    public String getDescrinescessidade() {
        return descrinescessidade;
    }
    public void setDescrinescessidade(String descrinescessidade) {
        this.descrinescessidade = descrinescessidade;
    }
    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    public byte[] getFotoDocumento() {
        return fotoDocumento;
    }
    public void setFotoDocumento(byte[] fotoDocumento) {
        this.fotoDocumento = fotoDocumento;
    }    
    public String getPix() {
        return this.pix;
    }
    public void setPix(String pix) {
        this.pix = pix;
    }
    public String getAgencia() {
        return this.agencia;
    }
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }     
    public String getConta() {
        return this.conta;
    }
    public void setConta(String conta) {
        this.conta = conta;
    }    
}
