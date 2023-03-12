package api.nxmu.festival.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    // ainda restam ligações fk    
    // n - 1 categoria 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // n - 1 participante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id")
    private Participante participante;

    // n - 1 jurado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jurado_id")
    private Jurado jurado;

    // n - 1 apresentacao
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apresentacao_id")
    private Apresentacao apresentacao;

    // n - 1 quesito
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quesito_id")
    private Quesito quesito;

}
