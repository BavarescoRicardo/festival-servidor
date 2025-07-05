package api.nxmu.festival.dto;

public class TabelaNotaDto {
    
    private long codigo;
    private double nota;
    private String participante;
    private String jurado;
    private String categoria;
    private String apresentacao;
    private String quesito;

    public TabelaNotaDto(long codigo, double nota, String participante, String jurado, String categoria, String apresentacao, String quesito) {
        this.codigo = codigo;
        this.nota = nota;
        this.participante = participante;
        this.jurado = jurado;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
        this.quesito = quesito;
    }    

    public TabelaNotaDto(double nota, String participante, String jurado, String categoria, String apresentacao, String quesito) {
        this.nota = nota;
        this.participante = participante;
        this.jurado = jurado;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
        this.quesito = quesito;
    }
    
    public TabelaNotaDto() {
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
    public String getParticipante() {
        return participante;
    }
    public void setParticipante(String participante) {
        this.participante = participante;
    }
    public String getJurado() {
        return jurado;
    }
    public void setJurado(String jurado) {
        this.jurado = jurado;
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
    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }
    public String getQuesito() {
        return quesito;
    }
    public void setQuesito(String quesito) {
        this.quesito = quesito;
    }

}
