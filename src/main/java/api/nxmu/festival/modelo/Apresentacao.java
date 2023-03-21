package api.nxmu.festival.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Apresentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String musica;
    private String tom;
    private String gravacao;
    private int ordem;
    private int senha;
    private int individuos;
    private int ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id")
    private Participante participante;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;       
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "apresentacao")
    private List<Nota> notas;     

    public Apresentacao(){
        
    }

    public Apresentacao(String musica, String tom, String gravacao,
    int individuos, Participante participante, Categoria categoria) {
        this.musica = musica;
        this.tom = tom;
        this.gravacao = gravacao;
        this.individuos = individuos;
        this.participante = participante;
        this.categoria = categoria;
    }

    public Apresentacao(String musica, String tom, String gravacao,
    int individuos, int participante, int categoria) {
        this.musica = musica;
        this.tom = tom;
        this.gravacao = gravacao;
        this.individuos = individuos;
      //  this.participante = participanteDB.findById(participante);
      //  this.categoria = categoriaeDB.findById(categoria);
    }
}
