package api.nxmu.festival.servicos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.nxmu.festival.modelo.Participante;
import api.nxmu.festival.repositorio.ParticipanteRepositorio;

@Service
public class ParticipanteServices {

    @Autowired
    private ParticipanteRepositorio participanteDB;

    // public void salvarFotoForm(MultipartFile img, Authentication auth) {
    //     try {
    //         UserDetails userd = (UserDetails)auth.getPrincipal();
    //         Participante participante = participanteDB.findByNomeLogin(userd.getUsername());
            
    //         if(participante == null){
    //             throw new Exception("Usuario não encontrado");
    //         }
    //         participanteDB.setFotoPerfil(img.getBytes());
    //         this.participanteDB.save(participante);    
    //     } catch (Exception e) {
    //         return;
    //     }
    // }

    public List<Participante> encontrar(){
        return participanteDB.findAll();
    }

    public boolean salvar(Participante participante){
        try {
            this.participanteDB.save(participante);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
