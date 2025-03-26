package api.nxmu.festival.dto.filtros;

public class FiltroParticipanteDto {
    private String textoFiltro;
    private Long codCategoria;
    private String pg;


    public FiltroParticipanteDto() {
    }
    
    public FiltroParticipanteDto(Long codCategoria) {
        this.codCategoria = codCategoria;
    }

    public FiltroParticipanteDto(String textoFiltro, Long codCategoria) {
        this.textoFiltro = textoFiltro;
        this.codCategoria = codCategoria;
    }

    public String getTextoFiltro() {
        return textoFiltro;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public void setTextoFiltro(String textoFiltro) {
        this.textoFiltro = textoFiltro;
    }

    public Long getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Long codCategoria) {
        this.codCategoria = codCategoria;
    }   
       
}
