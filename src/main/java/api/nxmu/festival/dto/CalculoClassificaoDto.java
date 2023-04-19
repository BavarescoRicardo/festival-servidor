package api.nxmu.festival.dto;

public class CalculoClassificaoDto {

    private Long categoria;

    public CalculoClassificaoDto(Long categoria) {
        this.categoria = categoria;
    }

    public CalculoClassificaoDto() {
    }

    public Long getCategoria() {
        return this.categoria;
    }    
    
}
