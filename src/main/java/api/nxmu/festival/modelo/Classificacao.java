package api.nxmu.festival.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
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

    // ainda restam ligações fk
    // n - 1 categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id")
    private Categoria categoria;

    // n - n participante
    @OneToMany(fetch = FetchType.LAZY)
    private List<Participante> participante;

    // 1 - 1 apresentacao
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apresentacao_id", referencedColumnName = "id")
    private Apresentacao apresentacao;
    
    public Classificacao(){
        
    }
}
