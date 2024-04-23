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
    private String categoria;
    @NotNull(message = "Jurado cannot be null")
    private String jurado;
    @NotNull(message = "Apresentacao cannot be null")
    private String apresentacao;

    public HistoricoNotaDto(double notaAfinacao, double notaRitmo, double notaInterpretacao, double notaDiccao, String categoria, String jurado, String apresentacao) {
        this.notaAfinacao = notaAfinacao;
        this.notaRitmo = notaRitmo;
        this.notaInterpretacao = notaInterpretacao;
        this.notaDiccao = notaDiccao;
        
        this.categoria = categoria;
        this.jurado = jurado;
        this.apresentacao = apresentacao;
    }
    
    public HistoricoNotaDto(String categoria, String jurado, String apresentacao) {        
        this.categoria = categoria;
        this.jurado = jurado;
        this.apresentacao = apresentacao;
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
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

}
