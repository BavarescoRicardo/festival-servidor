package api.nxmu.festival.dto.filtros;

public class FiltroApresentacaoDto {
    private String textoFiltro;
    private Long codCategoria;
    private String pg;
    private String ordem;

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public FiltroApresentacaoDto() {
    }
    
    public FiltroApresentacaoDto(Long codCategoria) {
        this.codCategoria = codCategoria;
    }

    public FiltroApresentacaoDto(String textoFiltro, Long codCategoria, String ordem) {
        this.textoFiltro = textoFiltro;
        this.codCategoria = codCategoria;
        this.ordem = ordem;
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
