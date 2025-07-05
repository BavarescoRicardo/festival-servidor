package api.nxmu.festival.dto;

import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class HistoricoNotaDto {

    @NotNull(message = "Jurado cannot be null")
    private String jurado;

    @NotNull(message = "Apresentação cannot be null")
    private String apresentacao;
    
    @NotNull(message = "Categoria cannot be null")
    private String categoria;    

    private Map<String, Double> notasPorQuesito = new HashMap<>();

    public HistoricoNotaDto(String jurado, String apresentacao, String categoria) {
        this.jurado = jurado;
        this.apresentacao = apresentacao;
        this.categoria = categoria;
    }

    public void adicionarNota(String quesito, double nota) {
        this.notasPorQuesito.put(quesito, nota);
    }

    public Map<String, Double> getNotasPorQuesito() {
        return notasPorQuesito;
    }

    public String getJurado() {
        return jurado;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public void setJurado(String jurado) {
        this.jurado = jurado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }    

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    public void setNotasPorQuesito(Map<String, Double> notasPorQuesito) {
        this.notasPorQuesito = notasPorQuesito;
    }
}
