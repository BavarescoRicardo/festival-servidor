package api.nxmu.festival.dto;

public class EnderecoDto {
    
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone;
    private Long participante;

    public EnderecoDto(){
        
    }

    public EnderecoDto(String endereco, String cidade, String estado, String cep, String telefone, Long participante) {
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
        this.participante = participante;
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
    public Long getParticipante() {
        return participante;
    }
    public void setParticipante(Long participante) {
        this.participante = participante;
    }

}
