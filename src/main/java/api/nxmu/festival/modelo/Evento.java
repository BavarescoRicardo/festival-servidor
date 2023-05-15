package api.nxmu.festival.modelo;

import java.time.LocalDate;
import java.util.Arrays;
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
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String local;
    private int ativo;
    private byte[] fotoEvento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    private List<Categoria> categorias;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    private List<Jurado> jurados; 

    public Evento(){

    }

    public Evento(String titulo, String descricao, LocalDate dataInicial, LocalDate dataFinal, String local){
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
