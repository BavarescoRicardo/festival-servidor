package api.nxmu.festival.modelo;


import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Audited
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double notafinal;
    private int ativo;

    // ainda restam ligações fk
    // n - 1 categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "apresentacao_id")
    private Apresentacao apresentacao;
    
    public Classificacao(double notafinal, Categoria categoria, Apresentacao apresentacao) {
        this.notafinal = notafinal;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
    }

    public Classificacao(){
        
    }
}
