package api.nxmu.festival.dto;

public class ListaCartaoApresentacaoDto {
    private Long codigo;
    private String musica;
    private String nomeartistico;
    private String autor;
    private int ordem;
    private String categoriaTitulo;
    private byte[] fotoPerfil;

    public ListaCartaoApresentacaoDto() {
    }    

    public ListaCartaoApresentacaoDto(Long codigo, String musica, String nomeartistico, String autor, int ordem,
            String categoriaTitulo, byte[] fotoPerfil) {
        this.codigo = codigo;
        this.musica = musica;
        this.nomeartistico = nomeartistico;
        this.autor = autor;
        this.ordem = ordem;
        this.categoriaTitulo = categoriaTitulo;
        this.fotoPerfil = fotoPerfil;
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

    public String getNomeartistico() {
        return nomeartistico;
    }

    public void setNomeartistico(String nomeartistico) {
        this.nomeartistico = nomeartistico;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getCategoriaTitulo() {
        return categoriaTitulo;
    }

    public void setCategoriaTitulo(String categoriaTitulo) {
        this.categoriaTitulo = categoriaTitulo;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    
       
}
