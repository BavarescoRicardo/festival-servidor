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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NotaFinal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double notaFinal;
    private int ativo;

    // n - 1 categoria 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // n - 1 jurado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jurado_id")
    private Jurado jurado;

    // n - 1 apresentacao
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apresentacao_id")
    private Apresentacao apresentacao;

}
