package api.nxmu.festival.dto;

public class JuradoDto {

    private String nome;
    private String contato;
    private String documento;
    private String observacao;

    public JuradoDto(String nome, String contato, String documento, String observacao) {
        this.nome = nome;
        this.contato = contato;
        this.documento = documento;
        this.observacao = observacao;
    }

    public JuradoDto() {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
