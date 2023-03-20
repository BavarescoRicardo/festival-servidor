package api.nxmu.festival.dto;

public class ApresentacaoDto {

    public ApresentacaoDto(String musica, String tom, String gravacao,
    int individuos, long participante, long categoria) {
    this.musica = musica;
    this.tom = tom;
    this.gravacao = gravacao;
    this.individuos = individuos;
    this.participante = participante;
    this.categoria = categoria;
    }

    public ApresentacaoDto(String musica, String tom, String gravacao, int ordem, int senha,
            int individuos, long participante, long categoria) {
        this.musica = musica;
        this.tom = tom;
        this.gravacao = gravacao;
        this.ordem = ordem;
        this.senha = senha;
        this.individuos = individuos;
        this.participante = participante;
        this.categoria = categoria;
    }

    private String musica;
    private String tom;
    private String gravacao;
    private int ordem;
    private int senha;
    private int individuos;

    private long participante;

    private long categoria;

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
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

    public long getParticipante() {
        return participante;
    }

    public void setParticipante(long participante) {
        this.participante = participante;
    }

    public long getCategoria() {
        return categoria;
    }

    public void setCategoria(long categoria) {
        this.categoria = categoria;
    }       
       
}
