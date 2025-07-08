package api.nxmu.festival.servicos;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.PerfilDto;
import api.nxmu.festival.modelo.Participante;
import api.nxmu.festival.modelo.Usuario;
import api.nxmu.festival.repositorio.ParticipanteRepositorio;
import api.nxmu.festival.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService
{    
    @Autowired
    private UsuarioRepositorio usuarioDB;
    
    @Autowired
    private ParticipanteRepositorio participanteDB;        

    public List<Usuario> encontrar(){
        return usuarioDB.findAll();
    }

    public Usuario selecionaUsuario(Long codigo){
        return usuarioDB.findById(codigo).get();
    }

    public Usuario selecionaUsuarioAutenticado(Authentication auth){
        UserDetails userd = (UserDetails)auth.getPrincipal();
        return usuarioDB.findByEmail(userd.getUsername()).orElse(new Usuario("Não encontrado"));
    }
    
    public PerfilDto selecionaPerfil(Authentication auth){
        PerfilDto dto = null;
        try {
            UserDetails userd = (UserDetails) auth.getPrincipal();
            Participante p = participanteDB.findByEmail(userd.getUsername());
            
            dto = PerfilDto.builder()
                .codigo(p.getId())
                .nomeArtistico(p.getApresentacao().getNomeartistico())
                .nomeResponsavel(p.getNomeResponsavel())
                .genero(p.getGenero())
                .nascimento(p.getNascimento())
                .documentorg(p.getDocumentorg())
                .email(p.getEmail())
                .necessidade(p.getNecessidade())
                .descrinescessidade(p.getDescrinescessidade())
                .cpf(p.getCpf())
                .pix(p.getPix())
                .banco(p.getBanco())
                .agencia(p.getAgencia())
                .conta(p.getConta())
                .apresentacao(p.getApresentacao().getMusica())
                .endereco(p.getEnderecos().get(0).getEndereco())
                .fotoPerfil(p.getFotoPerfil())

                // Adições abaixo:
                .status("Ativo")
                .classificacao("#-#")
                .progressoPerfil(calculaProgresso(p))
                .build();
        } catch (Exception e) {
            throw e;
        }
        return dto;
    }
    
    public PerfilDto atualizaPerfil(PerfilDto perfilDto){
        try {
            byte[] fotoPerfil = Base64.getDecoder().decode(perfilDto.getFotoPerfil());
            perfilDto.setFotoPerfil(fotoPerfil);

            Participante p = participanteDB.findById(perfilDto.getCodigo()).get();
            p.setFotoPerfil(fotoPerfil);
            participanteDB.save(p);
        } catch (Exception e) {
            throw e;
        }
        return perfilDto;
    }

    
    private int calculaProgresso(Participante p) {
        int total = 10;
        int preenchidos = 0;

        if (p.getNomeResponsavel() != null) preenchidos++;
        if (p.getEmail() != null) preenchidos++;
        if (p.getPix() != null) preenchidos++;
        if (p.getApresentacao() != null && p.getApresentacao().getMusica() != null) preenchidos++;
        if (p.getApresentacao().getNomeartistico() != null) preenchidos++;
        if (p.getDocumentorg() != null) preenchidos++;
        if (p.getEnderecos() != null && !p.getEnderecos().isEmpty()) preenchidos++;
        if (p.getFotoPerfil() != null) preenchidos++;
        if (p.getConta() != null) preenchidos++;
        if (p.getBanco() != null) preenchidos++;

        return (int) ((preenchidos / (double) total) * 100);
    }    

}

