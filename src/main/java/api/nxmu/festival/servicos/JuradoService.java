package api.nxmu.festival.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.JuradoDto;
import api.nxmu.festival.modelo.Jurado;
import api.nxmu.festival.repositorio.JuradoRepositorio;

@Service
public class JuradoService {

    @Autowired
    private JuradoRepositorio juradoDB;

    public Optional<Jurado> encontrarPorId(Long id){        
        return juradoDB.findById(id);
    }

    public List<JuradoDto> encontrar(){
        List<JuradoDto> listaDto = new ArrayList<JuradoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Jurado jurado: juradoDB.findAll()) {
            listaDto.add(new JuradoDto(
                jurado.getId(), jurado.getNome(), jurado.getContato(), 
                jurado.getDocumento(), jurado.getObservacao()));
        }

        return listaDto;
    }

    public boolean salvar(JuradoDto jurado){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Jurado e = new Jurado(
                jurado.getNome(), jurado.getContato(), 
                jurado.getDocumento(), jurado.getObservacao());

            this.juradoDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public JuradoDto atualizar(JuradoDto jurado, long id){
        try {
            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Jurado jur = this.encontrarPorId(id).get();
            jur.setNome(jurado.getNome());
            jur.setContato(jurado.getContato());
            jur.setDocumento(jurado.getDocumento()); 
            jur.setObservacao(jurado.getObservacao());

            this.juradoDB.save(jur);
        } catch (Exception e) {
            return null;
        }
        return jurado;
    }
    
    public ResponseEntity<String> remover(long id){
        try {
            
            Jurado jur = this.encontrarPorId(id).get();
            this.juradoDB.delete(jur);

            return ResponseEntity.ok().body("Removido objeto de id: "+id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
