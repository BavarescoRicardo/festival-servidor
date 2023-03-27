package api.nxmu.festival.dto;

public class TabelaNotaDto {
    
    private double nota;
    private String categoria;
    private String participante;
    private String jurado;
    private String apresentacao;
    private String quesito;

    public TabelaNotaDto(double nota, String categoria, String participante, String jurado, String apresentacao, String quesito) {
        this.nota = nota;
        this.categoria = categoria;
        this.participante = participante;
        this.jurado = jurado;
        this.apresentacao = apresentacao;
        this.quesito = quesito;
    }
    
    public TabelaNotaDto() {
    }
    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
