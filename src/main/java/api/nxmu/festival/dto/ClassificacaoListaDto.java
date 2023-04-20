package api.nxmu.festival.dto;

public class ClassificacaoListaDto {

    private Long codigo;
    private double notafinal;
    private String categoria;
    private String apresentacao;
    
    public ClassificacaoListaDto(){
        
    }

    public ClassificacaoListaDto(Long codigo, double notafinal, String categoria, String apresentacao) {
        this.codigo = codigo;
        this.notafinal = notafinal;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
    }

    public ClassificacaoListaDto(double notafinal, String categoria, String apresentacao) {
        this.notafinal = notafinal;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
    }
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }    

    public double getNotafinal() {
        return notafinal;
    }

    public void setNotafinal(double notafinal) {
        this.notafinal = notafinal;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }
}
