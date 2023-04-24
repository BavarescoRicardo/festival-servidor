package api.nxmu.festival.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ParticipanteDto {


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
    private byte[] fotoPerfil;

    public ParticipanteDto(String nomeArtistico, String nomeResponsavel, String genero, String nascimento,
            String documentorg, String email, String necessidade, String descrinescessidade, String endereco,
            String cidade, String estado, String cep, String telefone) {
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
    }
    public ParticipanteDto() {
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
    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
