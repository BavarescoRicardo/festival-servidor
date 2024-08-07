package api.nxmu.festival.dto;

import java.time.LocalDate;

public class CategoriaDto {

    private Long codigo;
    private String titulo;
    private String descricao;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private int ativo;
    private Long eventoCodigo;

    public CategoriaDto(Long codigo, String titulo, String descricao, LocalDate dataInicial, LocalDate dataFinal) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }
    
    public CategoriaDto(Long codigo, String titulo, String descricao, LocalDate dataInicial, LocalDate dataFinal,
    		int ativo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.setAtivo(ativo);
    }    

    public CategoriaDto() {
        
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getEventoCodigo() {
        return eventoCodigo;
    }

    public void setEventoCodigo(Long eventoCodigo) {
        this.eventoCodigo = eventoCodigo;
    }    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}    
}
