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
public class Jurado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String contato;
    private String documento;
    private String observacao;
    private int ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jurado")
    private List<Nota> notas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jurado")
    private List<NotaFinal> notasfinais;            
    
    public Jurado(String nome, String contato, String documento, String observacao) {
        this.nome = nome;
        this.contato = contato;
        this.documento = documento;
        this.observacao = observacao;
    }

    public Jurado(){
        
    }
    
}
