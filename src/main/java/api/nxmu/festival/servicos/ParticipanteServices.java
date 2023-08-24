package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.modelo.Participante;
import api.nxmu.festival.repositorio.ParticipanteRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteServices {

    private final ParticipanteRepositorio participanteDB;
    private final ApresentacaoServices apresentacaoServices;

    public Optional<Participante> encontrarPorId(Long id){        
        return participanteDB.findById(id);
    }

    public List<ParticipanteDto> encontrar(){
        List<ParticipanteDto> listaDto = new ArrayList<ParticipanteDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Participante participante: participanteDB.findAll()) {
            listaDto.add(new ParticipanteDto(
                participante.getId(), participante.getNomeArtistico(), participante.getNomeResponsavel(), 
                participante.getGenero(), participante.getNascimento(), participante.getDocumentorg(), 
                participante.getEmail(), participante.getNecessidade(), participante.getDescrinescessidade(),
                participante.getApresentacao().getId()));
        }

        return listaDto;
    }

    public List<ParticipanteDto> encontrarPorApresentacaoId(Long idApresentacao){
        List<ParticipanteDto> listaDto = new ArrayList<ParticipanteDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Participante participante: participanteDB.findAll()) {
            listaDto.add(new ParticipanteDto(
                participante.getId(), participante.getNomeArtistico(), participante.getNomeResponsavel(), 
                participante.getGenero(), participante.getNascimento(), participante.getDocumentorg(), 
                participante.getEmail(), participante.getNecessidade(), participante.getDescrinescessidade(),
                participante.getApresentacao().getId()));
        }

        return listaDto;
    }    

    public Long salvar(ParticipanteDto participante) {
        try {
            Participante p = new Participante(
                participante.getNomeArtistico(), participante.getNomeResponsavel(), 
                participante.getGenero(), participante.getNascimento(),
                participante.getDocumentorg(), participante.getEmail(), 
                participante.getNecessidade(), participante.getDescrinescessidade(),
                apresentacaoServices.encontrarPorId(participante.getApresentacao()).get()
            );
    
            Participante participanteSalvo = this.participanteDB.save(p);
            return participanteSalvo.getId();
        } catch (Exception e) {
            throw e;
        }
    }

    public ParticipanteDto atualizar(ParticipanteDto participante, long id){
        try {
            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Participante p = this.encontrarPorId(id).get();
            p.setNomeArtistico(participante.getNomeArtistico());
            p.setNomeResponsavel(participante.getNomeResponsavel()); 
            p.setGenero(participante.getGenero());
            p.setNascimento(participante.getNascimento());
            p.setDocumentorg(participante.getDocumentorg()); 
            p.setEmail(participante.getEmail());
            p.setNecessidade(participante.getNecessidade()); 
            p.setDescrinescessidade(participante.getDescrinescessidade());
            p.setApresentacao(apresentacaoServices.encontrarPorId(participante.getApresentacao()).get());

            this.participanteDB.save(p);    
        } catch (Exception e) {
            return null;
        }
        return participante;
    }
    
    public ResponseEntity<String> remover(long id){
        try {
            this.participanteDB.deleteById(id);

            return ResponseEntity.ok().body("Removido objeto de id: "+id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    public void savaImagem(Long idParticipante, MultipartFile files) {
        try {
            Participante participante = this.encontrarPorId(idParticipante).get();
            
            if((idParticipante == null) || !(participante.getId() > 0)){
                throw new Exception("Participante não encontrado");
            
            }

            // Se excecao nao disparada entao realiza tarefas
            participante.setFotoPerfil(files.getBytes());
            this.participanteDB.save(participante);    
        } catch (Exception e) {
            return;
        }
    }
    
    public void savaDocumento(Long idParticipante, MultipartFile files) {
        try {
            Participante participante = this.encontrarPorId(idParticipante).get();
            
            if((idParticipante == null) || !(participante.getId() > 0)){
                throw new Exception("Participante não encontrado");
            
            }

            // Se excecao nao disparada entao realiza tarefas
            participante.setFotoDocumento(files.getBytes());
            this.participanteDB.save(participante);    
        } catch (Exception e) {
            return;
        }
    }
     
}
