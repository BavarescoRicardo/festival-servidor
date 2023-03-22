package api.nxmu.festival.modelo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;
    private Date dataInicial;
    private Date dataFinal;
    private String local;
    private byte[] fotoEvento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    private List<Categoria> categorias;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    private List<Jurado> jurados; 

    public Evento(){

    }

    public Evento(String titulo, String descricao, Date dataInicial, Date dataFinal, String local){
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.local = local;
    }

    @Override
    public String toString() {
        return "Evento [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", dataInicial=" + dataInicial
                + ", dataFinal=" + dataFinal + ", local=" + local + ", fotoEvento=" + Arrays.toString(fotoEvento)
                + ", categorias=" + categorias + ", jurados=" + jurados + "]";
    }

    
}
