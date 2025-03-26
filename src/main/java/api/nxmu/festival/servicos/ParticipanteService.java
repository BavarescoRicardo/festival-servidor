package api.nxmu.festival.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.dto.filtros.FiltroParticipanteDto;
import api.nxmu.festival.modelo.Participante;
import api.nxmu.festival.modelo.Role;
import api.nxmu.festival.modelo.Usuario;
import api.nxmu.festival.repositorio.ParticipanteRepositorio;
import api.nxmu.festival.repositorio.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteService {
    private final EntityManager entityManager;
    private final ParticipanteRepositorio participanteDB;
    private final UsuarioRepositorio usuarioDB;
    private final ApresentacaoService apresentacaoService;
    private final PasswordEncoder passwordEncoder;

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
                participante.getCpf(), participante.getPix(), participante.getBanco(), participante.getAgencia(), participante.getConta(),
                participante.getApresentacao().getId(), new byte[0]));
        }

        return listaDto;
    }
    
    public ParticipanteDto encontrarParticipante(long id){
    	ParticipanteDto dto = null;
        try {
            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Participante p = this.encontrarPorId(id).get();
            dto = new ParticipanteDto(
	            p.getId(), p.getNomeArtistico(), p.getNomeResponsavel(), 
	            p.getGenero(), p.getNascimento(), p.getDocumentorg(), 
	            p.getEmail(), p.getNecessidade(), p.getDescrinescessidade(),
	            p.getCpf(), p.getPix(), p.getBanco(), p.getAgencia(), p.getConta(),
	            p.getApresentacao().getId(), new byte[0]);            

            this.participanteDB.save(p);    
        } catch (Exception e) {
            return null;
        }
        return dto;
    }    

    public List<ParticipanteDto> encontrarPorApresentacaoId(Long idApresentacao){
        List<ParticipanteDto> listaDto = new ArrayList<ParticipanteDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Participante participante: participanteDB.findAll()) {
            listaDto.add(new ParticipanteDto(
                participante.getId(), participante.getNomeArtistico(), participante.getNomeResponsavel(), 
                participante.getGenero(), participante.getNascimento(), participante.getDocumentorg(), 
                participante.getEmail(), participante.getNecessidade(), participante.getDescrinescessidade(),
                participante.getCpf(), participante.getPix(), participante.getBanco(), participante.getAgencia(), participante.getConta(),
                participante.getApresentacao().getId(), new byte[0]));
        }

        return listaDto;
    }    
    
    public List<ParticipanteDto> encontrarFiltrado(FiltroParticipanteDto filtro) {
        List<ParticipanteDto> listaDto = new ArrayList<ParticipanteDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 80);
        List<Participante> listaFiltrada = participanteDB.
                findAllFiltrado(filtro.getCodCategoria(), pageable).getContent();
        for (Participante participante : listaFiltrada) {
            listaDto.add(
            		new ParticipanteDto(
                            participante.getId(), participante.getNomeArtistico(), participante.getNomeResponsavel(), 
                            participante.getGenero(), participante.getNascimento(), participante.getDocumentorg(), 
                            participante.getEmail(), participante.getNecessidade(), participante.getDescrinescessidade(),
                            participante.getCpf(), participante.getPix(), participante.getBanco(), participante.getAgencia(), 
                            participante.getConta(), participante.getApresentacao().getId(), participante.getFotoPerfil()));
        }

        return listaDto;
    }
    

    @Transactional
    public Long salvar(ParticipanteDto participante) throws Exception{
        try {           
            var user = Usuario.builder()
                    .email(participante.getEmail())        
                    .senha(passwordEncoder.encode(
                    		participante.getSenha() == null || participante.getSenha().isBlank() ? 
                    				participante.getDocumentorg() : 
                    					participante.getSenha()))
                    .role(Role.USER)
                    .build();
            usuarioDB.save(user);
        	
            Participante p = new Participante(
                participante.getNomeArtistico(), participante.getNomeResponsavel(), 
                participante.getGenero(), participante.getNascimento(),
                participante.getDocumentorg(), participante.getEmail(), 
                participante.getNecessidade(), participante.getDescrinescessidade(), participante.getCpf(),
                participante.getPix(), participante.getBanco(), participante.getAgencia(), participante.getConta(),
                apresentacaoService.encontrarPorId(participante.getApresentacao()).get(), user
            );

            entityManager.persist(p);
            return p.getId();
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
            p.setApresentacao(apresentacaoService.encontrarPorId(participante.getApresentacao()).get());

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
