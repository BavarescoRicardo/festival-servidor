package api.nxmu.festival.modelo;

import java.util.Date;
import java.util.List;

import api.nxmu.festival.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;
    private Date dataInicial;
    private Date dataFinal;
    private int ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conta")
    private List<Usuario> usuarios;    

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Evento> eventos;

    public Conta() {

    }
}
