package api.nxmu.festival.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private String nomeartistico;
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

    public Apresentacao(){
        
    }
}
