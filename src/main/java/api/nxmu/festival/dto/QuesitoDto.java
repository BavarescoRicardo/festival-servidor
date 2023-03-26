package api.nxmu.festival.dto;

public class QuesitoDto {

    private Long codigo;
    private String descricao;
    private double peso;

    public QuesitoDto(Long codigo, String descricao, double peso) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.peso = peso;
    }

    public QuesitoDto() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }     
    
}
