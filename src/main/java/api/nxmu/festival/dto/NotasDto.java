package api.nxmu.festival.dto;

import jakarta.validation.constraints.NotNull;

public class NotasDto {
    @NotNull(message = "nota afinacao cannot be null")
    private double notaAfinacao;
    @NotNull(message = "nota diccao cannot be null")
    private double notaDiccao;
    @NotNull(message = "nota ritmo cannot be null")
    private double notaRitmo;
    @NotNull(message = "nota interpretacao cannot be null")
    private double notaInterpretacao;    
    
    @NotNull(message = "Categoria cannot be null")       
    private Long categoria;
    @NotNull(message = "Jurado cannot be null")
    private Long jurado;
    @NotNull(message = "Apresentacao cannot be null")
    private Long apresentacao;


    public NotasDto(double notaAfinacao, double notaDiccao, double notaRitmo, double notaInterpretacao, Long categoria, Long participante, Long jurado, Long apresentacao) {
        this.notaAfinacao = notaAfinacao;
        this.notaDiccao = notaDiccao;
        this.notaRitmo = notaRitmo;
        this.notaInterpretacao = notaInterpretacao;
        this.categoria = categoria;
        this.jurado = jurado;
        this.apresentacao = apresentacao;
    }
    
    public NotasDto() {
    }
    public double getNotaAfinacao() {
		return notaAfinacao;
	}

	public void setNotaAfinacao(double notaAfinacao) {
		this.notaAfinacao = notaAfinacao;
	}

	public double getNotaDiccao() {
		return notaDiccao;
	}

	public void setNotaDiccao(double notaDiccao) {
		this.notaDiccao = notaDiccao;
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

}
