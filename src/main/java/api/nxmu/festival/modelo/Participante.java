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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
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
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone;
    private String musica;
    private String gravacao;
    private String tom;
    private Long opcao_categoria;
    private Long opcao_participante;
    private byte[] fotoPerfil;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participante")
    private List<Apresentacao> apresentacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Classificacao classificacao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participante")
    private List<Nota> notas;     
        
    public Participante(Long id, byte[] fotoPerfil) {
        this.id = id;
        this.fotoPerfil = fotoPerfil;
    }
    public Participante(String nomeArtistico, String nomeResponsavel, String genero, String nascimento,
            String documentorg, String email, String necessidade, String descrinescessidade, String endereco,
            String cidade, String estado, String cep, String telefone, String musica, String gravacao, String tom,
            Long opcao_categoria, Long opcao_participante) {
        this.nomeArtistico = nomeArtistico;
        this.nomeResponsavel = nomeResponsavel;
        this.genero = genero;
        this.nascimento = nascimento;
        this.documentorg = documentorg;
        this.email = email;
        this.necessidade = necessidade;
        this.descrinescessidade = descrinescessidade;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
        this.musica = musica;
        this.gravacao = gravacao;
        this.tom = tom;
        this.opcao_categoria = opcao_categoria;
        this.opcao_participante = opcao_participante;
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
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getMusica() {
        return musica;
    }
    public void setMusica(String musica) {
        this.musica = musica;
    }
    public String getGravacao() {
        return gravacao;
    }
    public void setGravacao(String gravacao) {
        this.gravacao = gravacao;
    }
    public String getTom() {
        return tom;
    }
    public void setTom(String tom) {
        this.tom = tom;
    }
    public Long getOpcao_categoria() {
        return opcao_categoria;
    }
    public void setOpcao_categoria(Long opcao_categoria) {
        this.opcao_categoria = opcao_categoria;
    }
    public Long getOpcao_participante() {
        return opcao_participante;
    }
    public void setOpcao_participante(Long opcao_participante) {
        this.opcao_participante = opcao_participante;
    }
    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
