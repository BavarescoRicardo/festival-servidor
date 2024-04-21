package api.nxmu.festival.dto;

import jakarta.validation.constraints.NotNull;

public class HistoricoNotaDto {
    @NotNull(message = "nota cannot be null")
    private double notaAfinacao;
    @NotNull(message = "nota afinacao cannot be null")
    private double notaRitmo;
    @NotNull(message = "nota ritmo cannot be null")
    private double notaInterpretacao;
    @NotNull(message = "nota diccao cannot be null")
    private double notaDiccao;
    
    @NotNull(message = "Categoria cannot be null")
    private Long categoria;
    @NotNull(message = "Jurado cannot be null")
    private Long jurado;
    @NotNull(message = "Apresentacao cannot be null")
    private Long apresentacao;
    @NotNull(message = "Quesito cannot be null")
    private Long quesito;

    public HistoricoNotaDto(double notaAfinacao, double notaRitmo, double notaInterpretacao, double notaDiccao, Long categoria, Long participante, Long jurado, Long apresentacao, Long quesito) {
        this.notaAfinacao = notaAfinacao;
        this.notaRitmo = notaRitmo;
        this.notaInterpretacao = notaInterpretacao;
        this.notaDiccao = notaDiccao;
        
        this.categoria = categoria;
        this.jurado = jurado;
        this.apresentacao = apresentacao;
        this.quesito = quesito;
    }
    
    public double getNotaAfinacao() {
		return notaAfinacao;
	}

	public void setNotaAfinacao(double notaAfinacao) {
		this.notaAfinacao = notaAfinacao;
	}

	public double getNotaRitmo() {
		return notaRitmo;
	}

	public void setNotaRitmo(double notaRitmo) {
		this.notaRitmo = notaRitmo;
	}

	public double getNotaInterpretacao() {
		return notaInterpretacao;
	}

	public void setNotaInterpretacao(double notaInterpretacao) {
		this.notaInterpretacao = notaInterpretacao;
	}

	public double getNotaDiccao() {
		return notaDiccao;
	}

	public void setNotaDiccao(double notaDiccao) {
		this.notaDiccao = notaDiccao;
	}

	public HistoricoNotaDto() {
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
    public Long getQuesito() {
        return quesito;
    }
    public void setQuesito(Long quesito) {
        this.quesito = quesito;
    }

}
