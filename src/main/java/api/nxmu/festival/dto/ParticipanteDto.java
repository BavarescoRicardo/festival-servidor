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
    private byte[] fotoPerfil;

    public ParticipanteDto(String nomeArtistico, String nomeResponsavel, String genero, String nascimento,
            String documentorg, String email, String necessidade, String descrinescessidade) {
        this.nomeArtistico = nomeArtistico;
        this.nomeResponsavel = nomeResponsavel;
        this.genero = genero;
        this.nascimento = nascimento;
        this.documentorg = documentorg;
        this.email = email;
        this.necessidade = necessidade;
        this.descrinescessidade = descrinescessidade;
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
    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
