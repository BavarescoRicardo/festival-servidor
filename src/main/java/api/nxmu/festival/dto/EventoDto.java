package api.nxmu.festival.dto;

import java.util.Date;

public class EventoDto {

    private Long codigo;
    private String titulo;
    private String descricao;
    private Date dataInicial;
    private Date dataFinal;
    private String local;

    public EventoDto(Long codigo, String titulo, String descricao, Date dataInicial, Date dataFinal, String local) {
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

    public Date getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getLocal() {
        return this.local;
    }

    public void setLocal(String local) {
        this.local = local;
    }    
}
