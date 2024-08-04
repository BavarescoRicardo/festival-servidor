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
import jakarta.persistence.OneToOne;
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
public class Apresentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String musica;
    private String nomeartistico;
    private String tom;
    private String gravacao;
    private String autor;
    private String linkmusica;
    private int ordem;
    private int senha;
    private int individuos;
    private int ativo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "apresentacao")
    private List<Participante> participantes;    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;       
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "apresentacao")
    private List<Nota> notas; 
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "apresentacao")
    private List<NotaFinal> notasfinais;    
    
    @OneToOne(mappedBy = "apresentacao")  // Corrected annotation
    private Classificacao classificacao;    

    public Apresentacao(){
        
    }

    public Apresentacao(String musica, String nomeartistico, String tom, String gravacao, String autor,
    String linkmusica, int individuos, Categoria categoria) {
        this.musica = musica;
        this.nomeartistico = nomeartistico;
        this.tom = tom;
        this.gravacao = gravacao;
        this.autor = autor;
        this.linkmusica = linkmusica;
        this.individuos = individuos;
        this.categoria = categoria;
    }

    public Apresentacao(String musica, String nomeartistico, String tom, String gravacao, String autor,
    int individuos, Categoria categoria) {
        this.musica = musica;
        this.nomeartistico = nomeartistico;
        this.tom = tom;
        this.gravacao = gravacao;
        this.autor = autor;
        this.individuos = individuos;
        this.categoria = categoria;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMusica() {
		return musica;
	}

	public void setMusica(String musica) {
		this.musica = musica;
	}

	public String getNomeartistico() {
		return nomeartistico;
	}

	public void setNomeartistico(String nomeartistico) {
		this.nomeartistico = nomeartistico;
	}

	public String getTom() {
		return tom;
	}

	public void setTom(String tom) {
		this.tom = tom;
	}

	public String getGravacao() {
		return gravacao;
	}

	public void setGravacao(String gravacao) {
		this.gravacao = gravacao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getLinkmusica() {
		return linkmusica;
	}

	public void setLinkmusica(String linkmusica) {
		this.linkmusica = linkmusica;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public int getIndividuos() {
		return individuos;
	}

	public void setIndividuos(int individuos) {
		this.individuos = individuos;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public List<NotaFinal> getNotasfinais() {
		return notasfinais;
	}

	public void setNotasfinais(List<NotaFinal> notasfinais) {
		this.notasfinais = notasfinais;
	}   
    
    

}
