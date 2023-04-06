package api.nxmu.festival.dto;

public class NotaFinalDto {
    
    private Long jurado;
    private Long apresentacao;

    public NotaFinalDto(Long jurado, Long apresentacao) {

        this.jurado = jurado;
        this.apresentacao = apresentacao;
    }
    
    public NotaFinalDto() {
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
