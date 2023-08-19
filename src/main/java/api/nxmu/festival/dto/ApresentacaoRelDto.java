package api.nxmu.festival.dto;

public class ApresentacaoRelDto {

    private Long codigo;
    private String musica;
    private String nomeartistico;
    private String tom;
    private String cidadeUf;
    private String gravacao;
    private String autor;
    private int ordem;
    private int senha;
    private int individuos;
    private Long categoria;
    private String categoriaTitulo;    

    public ApresentacaoRelDto() {
    }

    public ApresentacaoRelDto(Long codigo, String musica, String nomeartistico, String tom, String gravacao,  String autor,
    int individuos, int ordem, int senha, String categoriaTitulo, String cidadeUf) {
        this.codigo = codigo;
        this.musica = musica;
        this.nomeartistico = nomeartistico;
        this.tom = tom;
        this.gravacao = gravacao;
        this.autor = autor;
        this.individuos = individuos;
        this.ordem = ordem;
        this.senha = senha;
        this.categoriaTitulo = categoriaTitulo;
        this.cidadeUf = cidadeUf;
    }

    public String getCategoriaTitulo() {
        return categoriaTitulo;
    }

    public void setCategoriaTitulo(String categoriaTitulo) {
        this.categoriaTitulo = categoriaTitulo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }    

    public String getMusica() {
        return musica;
    }

    public String getCidadeUf() {
        return cidadeUf;
    }

    public void setCidadeUf(String cidadeUf) {
        this.cidadeUf = cidadeUf;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getNomeartistico() {
        return nomeartistico;
    }

    public void setNomeartistico(String nomeartistico) {
        this.nomeartistico = nomeartistico;
    }    

    public String getTom() {
        return tom;
    }

    public void setTom(String tom) {
        this.tom = tom;
    }

    public String getGravacao() {
        return gravacao;
    }

    public void setGravacao(String gravacao) {
        this.gravacao = gravacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getIndividuos() {
        return individuos;
    }

    public void setIndividuos(int individuos) {
        this.individuos = individuos;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }       
       
}
