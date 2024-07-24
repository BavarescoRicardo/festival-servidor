package api.nxmu.festival.servicos;

import api.nxmu.festival.dto.EnderecoDto;
import api.nxmu.festival.dto.InscricaoDto;
import api.nxmu.festival.dto.ParticipanteDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InscricaoService {
    private final ApresentacaoService apresentacaoService;
    private final ParticipanteService participanteService;
    private final EnderecoService enderecoService;
    private final CategoriaService categoriaService;
    private final EmailService emailService;

    @Transactional
    public void salvarInscricao(InscricaoDto inscricaoDto) throws Exception {
        var apresentacaoSalvaId = apresentacaoService.salvar(inscricaoDto.getApresentacao());
        var enderecoDto = inscricaoDto.getEndereco();
        for (var participanteDto : inscricaoDto.getParticipantes()) {
            participanteDto.setApresentacao(apresentacaoSalvaId);
            var participanteSalvoId = participanteService.salvar(participanteDto);
            enderecoDto.setParticipante(participanteSalvoId);
            enderecoService.salvar(enderecoDto);
        }
        enviarEmail(inscricaoDto);
    }

    private void enviarEmail(InscricaoDto inscricaoDto) throws MessagingException, IOException {
        var parcitipantePrincipal = inscricaoDto.getParticipantes().stream().findFirst().get();

        var content = """
            <!DOCTYPE html>
            <html lang="pt-BR">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Relatório de Inscrição</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 0;
                        background-color: #f4f4f4;
                    }
                    .email-container {
                        background-color: #ffffff;
                        padding: 20px;
                        margin: 20px auto;
                        border: 1px solid #dddddd;
                        max-width: 600px;
                        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    }
                    h2 {
                        color: #333333;
                    }
                    p {
                        color: #555555;
                        line-height: 1.5;
                    }
                    .info-table {
                        width: 100%%;
                        border-collapse: collapse;
                    }
                    .info-table td {
                        padding: 8px;
                        border: 1px solid #dddddd;
                    }
                    .info-table td.label {
                        background-color: #f4f4f4;
                        font-weight: bold;
                    }
                    .bank-info {
                        margin-top: 5px;
                        border: 2px solid #000000;
                    }
                    h4 {
                        color: #333333;
                    }
                </style>
            </head>
            <body>
                <div class="email-container">
                    <h2>Relatório de Inscrição</h2>
                    <table class="info-table">
                        <tr>
                            <td class="label">Nome artístico:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Nascimento:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Responsável:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Cidade:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Email:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Contato tel:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Categoria:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Música:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">RG:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Tom:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Gravação:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Autor:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Participantes:</td>
                            <td>%s</td>
                        </tr>
                    </table>
                    <table class="info-table bank-info">
                        <tr>
                            <td class="label">PIX:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Agência:</td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td class="label">Conta:</td>
                            <td>%s</td>
                        </tr>
                    </table>
                    <h4>Segue anexo o documento do participante para validação.</h4>
                </div>
            </body>
            </html>
            """.formatted(
                inscricaoDto.getApresentacao().getNomeartistico(),
                formatarDataNascimento(parcitipantePrincipal.getNascimento()),
                parcitipantePrincipal.getNomeResponsavel(),
                montarEndereco(inscricaoDto.getEndereco()),
                parcitipantePrincipal.getEmail(),
                inscricaoDto.getEndereco().getTelefone(),
                categoriaService.encontrarPorId(inscricaoDto.getApresentacao().getCategoria()).get().getDescricao(),
                inscricaoDto.getApresentacao().getMusica(),
                parcitipantePrincipal.getDocumentorg(),
                inscricaoDto.getApresentacao().getTom(),
                inscricaoDto.getApresentacao().getGravacao(),
                inscricaoDto.getApresentacao().getAutor(),
                getParticipantesStr(inscricaoDto.getParticipantes()),
                parcitipantePrincipal.getPix(),
                parcitipantePrincipal.getAgencia(),
                parcitipantePrincipal.getConta()
        );
        this.emailService.sendMessageWithAttachment("[Inscrição FIMUSI 2024]", content, "foto_documento.png", getFileToSend(parcitipantePrincipal.getEmail(), inscricaoDto.getFotoDocumento()));
    }



    private String montarEndereco(EnderecoDto enderecoDto) {
        return "%s, %s, %s, %s.".formatted(enderecoDto.getCidade(), enderecoDto.getEstado(), enderecoDto.getCep());
    }

    private String getParticipantesStr(Collection<ParticipanteDto> participantes) {
        return participantes.stream().map(ParticipanteDto::getNomeResponsavel).collect(Collectors.joining(", "));
    }

    private String getFileToSend(String emailIdentificador, String base64String) throws IOException {
        String[] parts = base64String.split(",");
        String imageString = parts[1];

        String extension = "";
        if (parts[0].contains("/")) {
            extension = parts[0].split("/")[1].split(";")[0];
        }

        byte[] decodedBytes = Base64.getDecoder().decode(imageString);

        Path tempFile = createTempFileWithContent(emailIdentificador, decodedBytes, extension);
        return tempFile.toString();
    }

    private static Path createTempFileWithContent(String emailIdentificador, byte[] content, String extension) throws IOException {
        Path tempFile = Files.createTempFile("inscricao_" + emailIdentificador + "_", "." + extension);
        Files.write(tempFile, content);
        return tempFile;
    }

    private String formatarDataNascimento(String dataNascimento) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dataNascimento, inputFormatter);
        return date.format(outputFormatter);
    }

}
