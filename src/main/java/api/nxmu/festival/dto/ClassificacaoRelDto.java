package api.nxmu.festival.dto;

public class ClassificacaoRelDto {

    private Long codigo;
    private double notafinal;
    private String categoria;
    private String apresentacao;
    private String nomeArtistico;
    private String cidadeUf;    

    public ClassificacaoRelDto(){
        
    }

    public ClassificacaoRelDto(Long codigo, double notafinal, String categoria, 
        String apresentacao, String nomeArtistico, String cidadeUf) {
            this.codigo = codigo;
            this.notafinal = notafinal;
            this.categoria = categoria;
            this.apresentacao = apresentacao;
            this.nomeArtistico = nomeArtistico;
            this.cidadeUf = cidadeUf;
    }

    public ClassificacaoRelDto(double notafinal, String categoria, String apresentacao) {
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
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.apresentacao = nomeArtistico;
    }
    
    public String getCidadeUf() {
        return cidadeUf;
    }

    public void setCidadeUf(String cidadeUf) {
        this.cidadeUf = cidadeUf;
    }    
}
