package api.nxmu.festival.dto;

public class AtualizaNotaDto {
    private long codigo;
    private double nota;

    public AtualizaNotaDto(long codigo, double nota) {
        this.codigo = codigo;
        this.nota = nota;
    }
    
    public AtualizaNotaDto() {
    }
    public double getNota() {
        return nota;
    }
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }    
    public void setNota(double nota) {
        this.nota = nota;
    }    

}
