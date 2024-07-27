package api.nxmu.festival.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.ToString;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double notafinal;
    private int ativo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Apresentacao apresentacao;
    
    public Classificacao(double notafinal, Categoria categoria, Apresentacao apresentacao) {
        this.notafinal = notafinal;
        this.categoria = categoria;
        this.apresentacao = apresentacao;
    }

    public Classificacao() {
    }

    public void setNotafinal(double notafinal) {
        this.notafinal = notafinal;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
