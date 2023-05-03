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
public class Apresentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String musica;
    private String nomeartistico;
    private String tom;
    private String gravacao;
    private String autor;
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
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "apresentacao")
    private List<NotaFinal> notasfinais;        

    public Apresentacao(){
        
    }

    public Apresentacao(String musica, String nomeartistico, String tom, String gravacao, String autor,
    int individuos, Participante participante, Categoria categoria) {
        this.musica = musica;
        this.nomeartistico = nomeartistico;
        this.tom = tom;
        this.gravacao = gravacao;
        this.autor = autor;
        this.individuos = individuos;
        this.participante = participante;
        this.categoria = categoria;
    }

}
