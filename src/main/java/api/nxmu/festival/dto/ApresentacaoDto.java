package api.nxmu.festival.dto;

public class ApresentacaoDto {
    private Long codigo;
    private String musica;
    private String nomeartistico;
    private String tom;
    private String gravacao;
    private String autor;
    private String linkmusica;
    private int ordem;
    private int senha;
    private Long idEndereco;
    private String cidade;
    private Long categoria;
    private String categoriaTitulo;
    private String fotoDocumentoBase64;
    
    
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
	public Long getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
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
	public String getFotoDocumentoBase64() {
		return fotoDocumentoBase64;
	}
	public void setFotoDocumentoBase64(String fotoDocumentoBase64) {
		this.fotoDocumentoBase64 = fotoDocumentoBase64;
	}
	public ApresentacaoDto(Long codigo, String musica, String nomeartistico, String tom, String gravacao, String autor,
			String linkmusica, int ordem, int senha, Long idEndereco, String cidade, Long categoria,
			String categoriaTitulo, String fotoDocumentoBase64) {
		this.codigo = codigo;
		this.musica = musica;
		this.nomeartistico = nomeartistico;
		this.tom = tom;
		this.gravacao = gravacao;
		this.autor = autor;
		this.linkmusica = linkmusica;
		this.ordem = ordem;
		this.senha = senha;
		this.idEndereco = idEndereco;
		this.cidade = cidade;
		this.categoria = categoria;
		this.categoriaTitulo = categoriaTitulo;
		this.fotoDocumentoBase64 = fotoDocumentoBase64;
	}
	
	public ApresentacaoDto(String musica, String nomeartistico, String tom, String gravacao, String autor,
			String linkmusica, int ordem, int senha, Long idEndereco, String cidade, Long categoria,
			String categoriaTitulo) {
		this.musica = musica;
		this.nomeartistico = nomeartistico;
		this.tom = tom;
		this.gravacao = gravacao;
		this.autor = autor;
		this.linkmusica = linkmusica;
		this.ordem = ordem;
		this.senha = senha;
		this.idEndereco = idEndereco;
		this.cidade = cidade;
		this.categoria = categoria;
		this.categoriaTitulo = categoriaTitulo;
	}
	
	public ApresentacaoDto(Long codigo, String musica, String nomeartistico, String tom, String gravacao, String autor,
			String linkmusica, int ordem, int senha, Long idEndereco, String cidade, Long categoria) {
		this.codigo = codigo;
		this.musica = musica;
		this.nomeartistico = nomeartistico;
		this.tom = tom;
		this.gravacao = gravacao;
		this.autor = autor;
		this.linkmusica = linkmusica;
		this.ordem = ordem;
		this.senha = senha;
		this.idEndereco = idEndereco;
		this.cidade = cidade;
		this.categoria = categoria;
	}
	
	public ApresentacaoDto(String musica, String nomeartistico, String tom, String gravacao, String autor,
			String linkmusica, int ordem, int senha, Long idEndereco, String cidade, String categoriaTitulo) {
		this.musica = musica;
		this.nomeartistico = nomeartistico;
		this.tom = tom;
		this.gravacao = gravacao;
		this.autor = autor;
		this.ordem = ordem;
		this.senha = senha;
		this.idEndereco = idEndereco;
		this.cidade = cidade;
		this.categoriaTitulo = categoriaTitulo;
	}	
	
	public ApresentacaoDto(Long codigo, String musica, String nomeartistico, String tom, String gravacao, String autor,
			String cidade, Long idEndereco, int ordem, int senha,  String categoriaTitulo) {
		this.codigo = codigo;
		this.musica = musica;
		this.nomeartistico = nomeartistico;
		this.tom = tom;
		this.gravacao = gravacao;
		this.autor = autor;
		this.cidade = cidade;
		this.idEndereco = idEndereco;
		this.ordem = ordem;
		this.senha = senha;
		this.categoriaTitulo = categoriaTitulo;
	}	
	
	
	public ApresentacaoDto(Long codigo, String musica, String nomeartistico, String tom, String gravacao, String autor,
			String linkmusica, String cidade,  Long idEndereco, int ordem, int senha,  String categoriaTitulo) {
		this.codigo = codigo;
		this.musica = musica;
		this.nomeartistico = nomeartistico;
		this.tom = tom;
		this.gravacao = gravacao;
		this.autor = autor;
		this.linkmusica = linkmusica;
		this.cidade = cidade;
		this.idEndereco = idEndereco;
		this.ordem = ordem;
		this.senha = senha;
		this.categoriaTitulo = categoriaTitulo;
	}	
	
	public ApresentacaoDto(Long codigo, String musica, String nomeartistico, String tom, String gravacao, String autor,
			String cidade, Long idEndereco, int ordem,  int senha,  Long codCategoria) {
		this.codigo = codigo;
		this.musica = musica;
		this.nomeartistico = nomeartistico;
		this.tom = tom;
		this.gravacao = gravacao;
		this.autor = autor;
		this.cidade = cidade;
		this.ordem = ordem;
		this.senha = senha;
		this.categoria = codCategoria;
	}	
		
	public ApresentacaoDto( ) {
		
	}
	
	
}

