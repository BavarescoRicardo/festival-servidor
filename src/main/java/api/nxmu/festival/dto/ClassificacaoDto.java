package api.nxmu.festival.dto;

public class ClassificacaoDto {

    private Long codigo;
    private double notafinal;
    private Long categoria;
    private Long apresentacao;
    
    public ClassificacaoDto(){
        
    }

    public ClassificacaoDto(Long codigo, double notafinal, Long categoria, Long apresentacao) {
        this.codigo = codigo;
        this.notafinal = notafinal;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
    }

    public ClassificacaoDto(double notafinal, Long categoria, Long apresentacao) {
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

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    public Long getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(Long apresentacao) {
        this.apresentacao = apresentacao;
    }
}
