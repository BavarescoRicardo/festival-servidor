package api.nxmu.festival.dto;

import java.util.Map;

import jakarta.validation.constraints.NotNull;

public class NotasDto {    
    
    @NotNull(message = "Categoria cannot be null")       
    private Long categoria;
    @NotNull(message = "Jurado cannot be null")
    private Long jurado;
    @NotNull(message = "Apresentacao cannot be null")
    private Long apresentacao;
    @NotNull(message = "notas cannot be null")
    private Map<Long, Double> notasPorQuesito;

    public NotasDto(Long categoria, Long participante, Long jurado, Long apresentacao) {
        this.categoria = categoria;
        this.jurado = jurado;
        this.apresentacao = apresentacao;
    }
    
    public NotasDto(Long categoria, Long participante, Long jurado, Long apresentacao, Map<Long, Double> notasPorQuesito) {
        this.categoria = categoria;
        this.jurado = jurado;
        this.apresentacao = apresentacao;
        this.notasPorQuesito = notasPorQuesito;
    }    
    
    public NotasDto() {
    }

	public Long getCategoria() {
        return categoria;
    }
    public void setCategoria(Long categoria) {
        this.categoria = categoria;
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

	public Map<Long, Double> getNotasPorQuesito() {
		return notasPorQuesito;
	}

	public void setNotasPorQuesito(Map<Long, Double> notasPorQuesito) {
		this.notasPorQuesito = notasPorQuesito;
	}

    
}
