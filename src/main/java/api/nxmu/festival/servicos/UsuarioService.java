package api.nxmu.festival.servicos;

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
            UserDetails userd = (UserDetails)auth.getPrincipal();
            Participante p = participanteDB.findByEmail(userd.getUsername());

            dto = new PerfilDto(
                p.getId(), p.getApresentacao().getNomeartistico(), p.getNomeResponsavel(), 
                p.getGenero(), p.getNascimento(), p.getDocumentorg(), 
                p.getEmail(), p.getNecessidade(), p.getDescrinescessidade(),
                p.getCpf(), p.getPix(), p.getBanco(), p.getAgencia(), p.getConta(),
                p.getApresentacao().getMusica(), p.getEnderecos().get(0).getEndereco(), new byte[0]);			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
        return dto;
    }    

    

}

