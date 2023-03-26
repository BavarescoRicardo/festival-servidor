package api.nxmu.festival.dto;

public class JuradoDto {

    private Long codigo;
    private String nome;
    private String contato;
    private String documento;
    private String observacao;

    public JuradoDto(Long codigo, String nome, String contato, String documento, String observacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.contato = contato;
        this.documento = documento;
        this.observacao = observacao;
    }

    public JuradoDto() {
        
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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
