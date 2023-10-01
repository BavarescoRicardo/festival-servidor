package api.nxmu.festival.dto;

import jakarta.validation.constraints.NotNull;

public class NotaDto {
    @NotNull(message = "nota cannot be null")
    private double nota;
    @NotNull(message = "Categoria cannot be null")
    private Long categoria;
    @NotNull(message = "Participante cannot be null")
    private Long participante;
    @NotNull(message = "Jurado cannot be null")
    private Long jurado;
    @NotNull(message = "Apresentacao cannot be null")
    private Long apresentacao;
    @NotNull(message = "Quesito cannot be null")
    private Long quesito;

    public NotaDto(double nota, Long categoria, Long participante, Long jurado, Long apresentacao, Long quesito) {
        this.nota = nota;
        this.categoria = categoria;
        this.participante = participante;
        this.jurado = jurado;
        this.apresentacao = apresentacao;
        this.quesito = quesito;
    }
    
    public NotaDto() {
    }
    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
    public Long getCategoria() {
        return categoria;
    }
    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }
    public Long getParticipante() {
        return participante;
    }
    public void setParticipante(Long participante) {
        this.participante = participante;
    }
    public Long getJurado() {
        return jurado;
    }
    public void setJurado(Long jurado) {
        this.jurado = jurado;
    }
    public Long getApresentacao() {
        return apresentacao;
    }
    public void setApresentacao(Long apresentacao) {
        this.apresentacao = apresentacao;
    }
    public Long getQuesito() {
        return quesito;
    }
    public void setQuesito(Long quesito) {
        this.quesito = quesito;
    }

}
