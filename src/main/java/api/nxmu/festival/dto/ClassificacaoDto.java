package api.nxmu.festival.dto;

import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.modelo.Categoria;

public class ClassificacaoDto {

    private Long codigo;
    private double notafinal;
    private Categoria categoria;
    private Apresentacao apresentacao;
    
    public ClassificacaoDto(){
        
    }

    public ClassificacaoDto(Long codigo, double notafinal, Categoria categoria, Apresentacao apresentacao) {
        this.codigo = codigo;
        this.notafinal = notafinal;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
    }

    public ClassificacaoDto(double notafinal, Categoria categoria, Apresentacao apresentacao) {
        this.notafinal = notafinal;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
    }
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }    

    public double getNotafinal() {
        return notafinal;
    }

    public void setNotafinal(double notafinal) {
        this.notafinal = notafinal;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Apresentacao getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(Apresentacao apresentacao) {
        this.apresentacao = apresentacao;
    }
}
