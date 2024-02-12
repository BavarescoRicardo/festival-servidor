package api.nxmu.festival.modelo;

import java.util.List;

import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Audited
public class Quesito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;
    private double peso;
    private int ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quesito")
    private List<Nota> notas;

    public Quesito(String descricao, double peso) {
        this.descricao = descricao;
        this.peso = peso;
    }

    public Quesito() {
    }     
    
}
