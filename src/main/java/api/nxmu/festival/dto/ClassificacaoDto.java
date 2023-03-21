package api.nxmu.festival.dto;

public class ClassificacaoDto {

    private double notafinal;
    private Long categoria;
    private Long apresentacao;
    
    public ClassificacaoDto(){
        
    }

    public ClassificacaoDto(double notafinal, Long categoria, Long apresentacao) {
        this.notafinal = notafinal;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
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
