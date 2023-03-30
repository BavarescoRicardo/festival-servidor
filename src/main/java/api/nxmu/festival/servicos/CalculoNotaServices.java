package api.nxmu.festival.servicos;

import java.util.Optional;

import org.springframework.stereotype.Service;

import api.nxmu.festival.modelo.Nota;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculoNotaServices {

    private final NotaServices notaDB;

    public Optional<Nota> encontrarPorId(Long id){        
        return notaDB.encontrarPorId(id);
    }

    public void calcularNota(){
        // identificar código da apresentação  
        // Realiza cálculo da média final da nota
        // Deve iterar por todos os jurados
        // Deve iterar por todos os quesitos
        // Adicionar condição para desconsider jurados que der nota mais alta e também o que der a nota mais baixa
        // O resultado do  calculo é a média de notas de todos os jurados e de todos os quesitos Ex..
            
            // -- for jurado 1 -- n
                // -- for quesito 1 -- n 
                    // sum += notaQuesito
                // -- fim for quesitos
                // -- resultado sum dividido por numero de quesitos

            // -- fim for jurados

            // -- resultado sum dividido por numero de jurados
        // -- resultado final sera a média de notas da apresentação
    }
    
}
