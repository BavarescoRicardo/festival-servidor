package api.nxmu.festival.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RespostaRequestDto<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T conteudo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensagem;

    public RespostaRequestDto(final T conteudo) {
        this.conteudo = conteudo;
    }

    public RespostaRequestDto(final String mensagem) {
        this.mensagem = mensagem;
    }
}
