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
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double notafinal;

    // n - 1 categoria 
    // n - n participante
    // 1 - 1 apresentacao
    
}
