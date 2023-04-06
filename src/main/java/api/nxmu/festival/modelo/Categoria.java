package api.nxmu.festival.modelo;

import java.util.Date;
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
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;
    private Date dataInicial;
    private Date dataFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<Apresentacao> apresentacoes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<Classificacao> classificacaoes;    

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<Nota> notas;    

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<NotaFinal> notasfinais;        

    public Categoria(String titulo, String descricao, Date dataInicial, Date dataFinal, Evento evento) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.evento = evento;
    }

    public Categoria(String titulo, String descricao, Date dataInicial, Date dataFinal) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }    

    public Categoria(){
        
    }
    
}
