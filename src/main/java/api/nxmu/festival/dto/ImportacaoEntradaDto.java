package api.nxmu.festival.dto;

public class ImportacaoEntradaDto {

    private String descricao;
    private int ses;

    public ImportacaoEntradaDto(String descricao, int ses) {
        this.descricao = descricao;
        this.ses = ses;
    }

    public ImportacaoEntradaDto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSes() {
        return this.ses;
    }

    public void setSes(int ses) {
        this.ses = ses;
    }     
    
}
