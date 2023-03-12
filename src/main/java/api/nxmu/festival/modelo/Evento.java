package api.nxmu.festival.modelo;

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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    private List<Categoria> categorias;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    private List<Jurado> jurados; 

    public Evento(){

    }

    public Evento(String titulo, String descricao, Date dataInicial, Date dataFinal){
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }
}
