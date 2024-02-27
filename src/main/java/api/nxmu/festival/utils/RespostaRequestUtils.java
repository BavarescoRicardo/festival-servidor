package api.nxmu.festival.utils;

import org.springframework.http.ResponseEntity;

import api.nxmu.festival.dto.RespostaRequestDto;

public class RespostaRequestUtils {

    public static <T> ResponseEntity<RespostaRequestDto<T>> buildResponseOk (T conteudo) {
        return  ResponseEntity.ok(new RespostaRequestDto<T>(conteudo));
    }

    public static <T> ResponseEntity<RespostaRequestDto<T>> buildResponseInternalError (String mensagem) {
        var vespostaRequestDto = new RespostaRequestDto<T>();
        vespostaRequestDto.setMensagem(mensagem);
        return  ResponseEntity.badRequest().body(vespostaRequestDto);
    }

}
