package api.nxmu.festival.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Nota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double nota;

    // 1 - 1 categoria 
    // 1 - 1 participante
    // 1 - 1 jurado
    // 1 - 1 apresentacao
    // 1 - 1 quesito
}
