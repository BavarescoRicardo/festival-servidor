package api.nxmu.festival.dto;

public class QuesitoDto {

    private String descricao;
    private double peso;

    public QuesitoDto(String descricao, double peso) {
        this.descricao = descricao;
        this.peso = peso;
    }

    public QuesitoDto() {
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
