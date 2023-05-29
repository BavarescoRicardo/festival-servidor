package api.nxmu.festival.dto.filtros;

public class FiltroClassificacaoDto {
    private String textoFiltro;
    private Long codCategoria;
    private String pg;

    public FiltroClassificacaoDto() {
    }
    
    public FiltroClassificacaoDto(Long codCategoria) {
        this.codCategoria = codCategoria;
    }

    public FiltroClassificacaoDto(String textoFiltro, Long codCategoria) {
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
