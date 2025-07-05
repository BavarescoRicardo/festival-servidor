package api.nxmu.festival.dto;

public class ClassificacaoListaDto {

    private Long codigo;
    private double notafinal;
    private String categoria;
    private String apresentacao;
    private String nomeArtistico;
    
    public ClassificacaoListaDto(){
        
    }

    public ClassificacaoListaDto(Long codigo, double notafinal, String apresentacao, String nomeArtistico, String categoria) {
        this.codigo = codigo;
        this.notafinal = notafinal;
        this.apresentacao = apresentacao;
        this.nomeArtistico = nomeArtistico;
        this.categoria = categoria;
    }

    public ClassificacaoListaDto(double notafinal, String apresentacao) {
        this.notafinal = notafinal;
        this.apresentacao = apresentacao;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.apresentacao = nomeArtistico;
    }    
}
