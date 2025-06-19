package api.nxmu.festival.dto;

public class MusicaDto {
    private Long codigo;
    private String musica;
    private String autor;
    private String linkmusica;
    private Long categoria;
    private String categoriaTitulo;
    
	public MusicaDto() {
	
	}    
    
	public MusicaDto(Long codigo, String musica, String autor, String linkmusica, Long categoria) {
		this.codigo = codigo;
		this.musica = musica;
		this.autor = autor;
		this.linkmusica = linkmusica;
		this.categoria = categoria;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getMusica() {
		return musica;
	}
	public void setMusica(String musica) {
		this.musica = musica;
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
	public Long getCategoria() {
		return categoria;
	}
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}
	public String getCategoriaTitulo() {
		return categoriaTitulo;
	}
	public void setCategoriaTitulo(String categoriaTitulo) {
		this.categoriaTitulo = categoriaTitulo;
	}
    
    
}
