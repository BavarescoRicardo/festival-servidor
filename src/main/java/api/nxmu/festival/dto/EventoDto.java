package api.nxmu.festival.dto;

import java.time.LocalDate;
public class EventoDto {

    private Long codigo;
    private String titulo;
    private String descricao;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String local;

    public EventoDto(Long codigo, String titulo, String descricao, LocalDate dataInicial, LocalDate dataFinal, String local) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.local = local;
    }

    public EventoDto() {
        
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public String getLocal() {
        return this.local;
    }

    public void setLocal(String local) {
        this.local = local;
    }    
}
