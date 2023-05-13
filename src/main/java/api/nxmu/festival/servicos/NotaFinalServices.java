package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import api.nxmu.festival.modelo.NotaFinal;
import api.nxmu.festival.repositorio.NotaFinalRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotaFinalServices {
    private final NotaFinalRepositorio notaFinalDB;

    public Optional<NotaFinal> encontrarPorId(Long id){        
        return notaFinalDB.findById(id);
    }

    public List<NotaFinal> encontrar(){
        return notaFinalDB.findAll();
    }  
    
    public List<NotaFinal> encontrarPorApresentacao(long codigoApresentacao){        
        return  notaFinalDB.findAllByApresentacao(codigoApresentacao);
    }

    public List<NotaFinal> encontrarPorApresentacaoeJurado(long codigoApresentacao, long codigoJurado){        
        return  notaFinalDB.findAllByApresentacaoJurado(codigoApresentacao, codigoJurado);
    }
    
    public List<NotaFinal> encontrarPorCategoria(long codigoCategoria){        
        return  notaFinalDB.findAllByCategoria(codigoCategoria);
    }
    
    public ResponseEntity<String> removerNotaFinal(long idApresentacao, long idJurado){
        try {
            
            NotaFinal notaFinal = this.encontrarPorApresentacaoeJurado(idApresentacao, idJurado).get(0);
            this.notaFinalDB.delete(notaFinal);

            return ResponseEntity.ok().body("Removida nota final de id: "+idApresentacao + " jurado: " + idJurado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
