package api.nxmu.festival.dto;

public class AtualizaClassificacaoDto {

    private Long codigo;
    private double notafinal;
    
    public AtualizaClassificacaoDto(){
        
    }

    public AtualizaClassificacaoDto(Long codigo, double notafinal) {
        this.codigo = codigo;
        this.notafinal = notafinal;
    }

    public AtualizaClassificacaoDto(double notafinal) {
        this.notafinal = notafinal;
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
}
