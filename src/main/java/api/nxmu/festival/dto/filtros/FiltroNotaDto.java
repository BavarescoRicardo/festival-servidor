package api.nxmu.festival.dto.filtros;

public class FiltroNotaDto {
    private Long codCategoria;
    private String pg;

    public FiltroNotaDto() {
    }
    
    public FiltroNotaDto(Long codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public Long getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Long codCategoria) {
        this.codCategoria = codCategoria;
    }   
       
}
