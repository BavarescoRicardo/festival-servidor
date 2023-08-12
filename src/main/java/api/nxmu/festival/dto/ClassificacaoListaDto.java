package api.nxmu.festival.dto;

public class ClassificacaoListaDto {

    private Long codigo;
    private double notafinal;
    private String categoria;
    private String apresentacao;
    private String nomeArtistico;
    
    public ClassificacaoListaDto(){
        
    }

    public ClassificacaoListaDto(Long codigo, double notafinal, String categoria, String apresentacao, String nomeArtistico) {
        this.codigo = codigo;
        this.notafinal = notafinal;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
        this.nomeArtistico = nomeArtistico;
    }

    public ClassificacaoListaDto(double notafinal, String categoria, String apresentacao) {
        this.notafinal = notafinal;
        this.categoria = categoria;
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
        return apresentacao;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.apresentacao = nomeArtistico;
    }    
}
