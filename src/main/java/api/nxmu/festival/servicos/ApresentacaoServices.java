package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ApresentacaoDto;
import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.repositorio.ApresentacaoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApresentacaoServices {

    private final ApresentacaoRepositorio apresentacaoDB;
    private final CategoriaServices categoriaServices;
    private final ParticipanteServices participanteServices;

    public Optional<Apresentacao> encontrarPorId(Long id){        
        return apresentacaoDB.findById(id);
    }

    public List<ApresentacaoDto> encontrar(){
        List<ApresentacaoDto> listaDto = new ArrayList<ApresentacaoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: apresentacaoDB.findAll()) {
            listaDto.add(new ApresentacaoDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(), 
                apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getIndividuos(), 
                apresentacao.getParticipante().getId(), apresentacao.getCategoria().getId()));
        }

        return listaDto;
    }

    public boolean salvar(ApresentacaoDto apresentacaoDto){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Apresentacao apresentacao = Apresentacao.builder()
                .musica(apresentacaoDto.getMusica())
                .nomeartistico(apresentacaoDto.getNomeartistico())
                .tom(apresentacaoDto.getTom())
                .gravacao(apresentacaoDto.getGravacao())
                .autor(apresentacaoDto.getAutor())
                .individuos(apresentacaoDto.getIndividuos())
                .participante(participanteServices.encontrarPorId(apresentacaoDto.getParticipante()).get())
                .categoria(categoriaServices.encontrarPorId(apresentacaoDto.getCategoria()).get())
                .build();
            this.apresentacaoDB.save(apresentacao);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ApresentacaoDto atualizar(ApresentacaoDto apresentacaoDto, long id){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Apresentacao apresentacao = this.encontrarPorId(id).get();

            // Atualizar modelo com campos do objeto dto
            apresentacao.setMusica(apresentacaoDto.getMusica());
            apresentacao.setNomeartistico(apresentacaoDto.getNomeartistico());
            apresentacao.setTom(apresentacaoDto.getTom());
            apresentacao.setGravacao(apresentacaoDto.getGravacao());
            apresentacao.setAutor(apresentacaoDto.getAutor());
            apresentacao.setIndividuos(apresentacaoDto.getIndividuos());
            apresentacao.setParticipante(participanteServices.encontrarPorId(apresentacaoDto.getParticipante()).get());
            apresentacao.setCategoria(categoriaServices.encontrarPorId(apresentacaoDto.getCategoria()).get());
            this.apresentacaoDB.save(apresentacao);
            return apresentacaoDto;
        } catch (Exception e) {
            return null;
        }
    }
    
}
