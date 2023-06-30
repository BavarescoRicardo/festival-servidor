package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.EnderecoDto;
import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.modelo.Endereco;
import api.nxmu.festival.repositorio.EnderecoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoServices {

    private final EnderecoRepositorio enderecoDB;
    private final ParticipanteServices participanteServices;
    private final ApresentacaoServices apresentacaoServices;

    public Optional<Endereco> encontrarPorId(Long id){        
        return enderecoDB.findById(id);
    }

    public Optional<Endereco> encontrarPorParticipante(Long id){        
        try {
            return enderecoDB.findByIdParticipante(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
            
        }
    }

    public void removerPorParticipante(Long id){        
        try {
            enderecoDB.removeByIdParticipante(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    


    public List<EnderecoDto> encontrar(){
        List<EnderecoDto> listaDto = new ArrayList<EnderecoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Endereco endereco: enderecoDB.findAll()) {
            listaDto.add(new EnderecoDto(
                endereco.getEndereco(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(),
                endereco.getTelefone(), endereco.getParticipante().getId()));
        }

        return listaDto;
    }

    public boolean salvar(EnderecoDto endereco){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Endereco e = new Endereco(
                endereco.getEndereco(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(),
                endereco.getTelefone(), participanteServices.encontrarPorId(endereco.getParticipante()).get());

            this.enderecoDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public EnderecoDto atualizar(EnderecoDto endereco, long id){
        try {
            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Endereco e = this.encontrarPorId(id).get();
            e.setEndereco(endereco.getEndereco()); 
            e.setCidade(endereco.getCidade()); 
            e.setEstado(endereco.getEstado());
            e.setCep(endereco.getCep());
            e.setTelefone(endereco.getTelefone()); 
            e.setParticipante(participanteServices.encontrarPorId(endereco.getParticipante()).get());

            this.enderecoDB.save(e);    
        } catch (Exception e) {
            return null;
        }
        return endereco;
    }

    
    public ResponseEntity<String> remover(long id){
        try {
            // Encontra objetos da lista de participantes pelo id da apresentacao
            List<ParticipanteDto> participantes = participanteServices.encontrarPorApresentacaoId(id);
            // remover endereco de todos os participantes 
            for (ParticipanteDto participanteDto : participantes) {
                // enderecoDB.removeByIdParticipante(participanteDto.getCodigo());                 
                Endereco endereco = enderecoDB.findById(participanteDto.getCodigo()).get();
                enderecoDB.deleteById(endereco.getId());
                // remover participante 
                participanteServices.remover(participanteDto.getCodigo());
                apresentacaoServices.remover(id);
            }

            // por fim remover apresentacao
            apresentacaoServices.remover(id);
            

            return ResponseEntity.ok().body("Removido objeto de id: "+id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
