package api.nxmu.festival.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.EnderecoDto;
import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.modelo.Endereco;
import api.nxmu.festival.repositorio.EnderecoRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepositorio enderecoDB;
    private final ParticipanteService participanteService;
    private final ApresentacaoService apresentacaoService;
    private final EntityManager entityManager;

    public Optional<Endereco> encontrarPorId(Long id) {
        return enderecoDB.findById(id);
    }

    public Optional<Endereco> encontrarPorParticipante(Long id) {
        try {
            return enderecoDB.findByIdParticipante(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;

        }
    }

    public void removerPorParticipante(Long id) {
        try {
            enderecoDB.removeByIdParticipante(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public List<EnderecoDto> encontrar() {
        List<EnderecoDto> listaDto = new ArrayList<EnderecoDto>();

        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for (Endereco endereco : enderecoDB.findAll()) {
            listaDto.add(new EnderecoDto(
                    endereco.getEndereco(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(),
                    endereco.getTelefone(), endereco.getParticipante().getId()));
        }

        return listaDto;
    }

    @Transactional
    public boolean salvar(EnderecoDto endereco) {
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Endereco e = new Endereco(
                    endereco.getEndereco(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(),
                    endereco.getTelefone(), participanteService.encontrarPorId(endereco.getParticipante()).get());

            Thread.sleep(100);
            entityManager.persist(e);
            Thread.sleep(1000);
            return e.getId() != null;
        } catch (Exception e) {
            return false;
        }
    }

    public EnderecoDto atualizar(EnderecoDto endereco, long id) {
        try {
            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Endereco e = this.encontrarPorId(id).get();
            if ((endereco.getEndereco() != null) && (endereco.getEndereco().length() > 0)) {
                e.setEndereco(endereco.getEndereco());
            }

            if ((endereco.getCidade() != null) && (endereco.getCidade().length() > 0)) {
                e.setCidade(endereco.getCidade());
            }

            this.enderecoDB.save(e);
        } catch (Exception e) {
            return null;
        }
        return endereco;
    }


    public ResponseEntity<String> remover(long id) {
        try {
            // Encontra objetos da lista de participantes pelo id da apresentacao
            List<ParticipanteDto> participantes = participanteService.encontrarPorApresentacaoId(id);
            // remover endereco de todos os participantes 
            for (ParticipanteDto participanteDto : participantes) {
                // enderecoDB.removeByIdParticipante(participanteDto.getCodigo());                 
                Endereco endereco = enderecoDB.findById(participanteDto.getCodigo()).get();
                enderecoDB.deleteById(endereco.getId());
                // remover participante 
                participanteService.remover(participanteDto.getCodigo());
                apresentacaoService.remover(id);
            }

            // por fim remover apresentacao
            apresentacaoService.remover(id);


            return ResponseEntity.ok().body("Removido objeto de id: " + id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
