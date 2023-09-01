package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ApresentacaoDto;
import api.nxmu.festival.dto.ApresentacaoRelDto;
import api.nxmu.festival.dto.ListaCartaoApresentacaoDto;
import api.nxmu.festival.dto.filtros.FiltroApresentacaoDto;
import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.repositorio.ApresentacaoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApresentacaoServices {

    private final ApresentacaoRepositorio apresentacaoDB;
    private final CategoriaServices categoriaServices;

    public Optional<Apresentacao> encontrarPorId(Long id){        
        return apresentacaoDB.findById(id);
    }

    public List<ApresentacaoDto> encontrar(){
        List<ApresentacaoDto> listaDto = new ArrayList<ApresentacaoDto>();
        Pageable pageable = PageRequest.of(0, 50, Sort.by("ordem"));
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: apresentacaoDB.findAllOrdenado(pageable)) {
            listaDto.add(new ApresentacaoDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(), 
                apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getIndividuos(), 
                apresentacao.getOrdem(), apresentacao.getSenha(), apresentacao.getCategoria().getTitulo()));
        }

        return listaDto;
    }

    public List<ApresentacaoDto> encontrarFiltrado(FiltroApresentacaoDto filtro){
        List<ApresentacaoDto> listaDto = new ArrayList<ApresentacaoDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 60, Sort.by(filtro.getOrdem()));
        List<Apresentacao> listaFiltrada = apresentacaoDB.
            findAllFiltrado(
                filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();        
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: listaFiltrada) {
            listaDto.add(new ApresentacaoDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(), 
                apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getIndividuos(), 
                apresentacao.getOrdem(), apresentacao.getSenha(), apresentacao.getCategoria().getTitulo()));
        }

        return listaDto;
    }    

    public List<ApresentacaoRelDto> encontrarRel(FiltroApresentacaoDto filtro){
        List<ApresentacaoRelDto> listaDto = new ArrayList<ApresentacaoRelDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 60, Sort.by(filtro.getOrdem()));
        List<Apresentacao> listaFiltrada = apresentacaoDB.
            findAllFiltrado(
                filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();        
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: listaFiltrada) {
            listaDto.add(new ApresentacaoRelDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(), 
                apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getIndividuos(), 
                apresentacao.getOrdem(), apresentacao.getSenha(), apresentacao.getCategoria().getTitulo(), "cidade"));
        }

        return listaDto;
    }  
    
    public List<ApresentacaoRelDto> encontrarRelBanda(FiltroApresentacaoDto filtro){
        List<ApresentacaoRelDto> listaDto = new ArrayList<ApresentacaoRelDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 200, Sort.by(filtro.getOrdem()));
        List<Apresentacao> listaFiltrada = apresentacaoDB.
            findAllFiltrado(
                filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();        
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: listaFiltrada) {
            listaDto.add(new ApresentacaoRelDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(), 
                apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getLinkmusica(), apresentacao.getIndividuos(), 
                apresentacao.getOrdem(), apresentacao.getSenha(), apresentacao.getCategoria().getTitulo(), "cidade"));
        }

        return listaDto;
    }      

    public List<ApresentacaoDto> encontrarPorCategoria(long codCategoria){
        List<ApresentacaoDto> listaDto = new ArrayList<ApresentacaoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: apresentacaoDB.findAllByCategoria(codCategoria)) {
            listaDto.add(new ApresentacaoDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(), 
                apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getIndividuos(), 
                apresentacao.getCategoria().getTitulo(), apresentacao.getCategoria().getId()));
        }

        return listaDto;
    }

    public Long salvar(ApresentacaoDto apresentacaoDto) throws Exception{
        Apresentacao apresentacaoSalva = null;
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Apresentacao apresentacao = Apresentacao.builder()
                .musica(apresentacaoDto.getMusica())
                .nomeartistico(apresentacaoDto.getNomeartistico())
                .tom(apresentacaoDto.getTom())
                .gravacao(apresentacaoDto.getGravacao())
                .autor(apresentacaoDto.getAutor())
                .individuos(apresentacaoDto.getIndividuos())
                .categoria(categoriaServices.encontrarPorId(apresentacaoDto.getCategoria()).get())
                .build();
                apresentacaoSalva = this.apresentacaoDB.save(apresentacao);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
        return apresentacaoSalva.getId();
    }

    public Long salvarImport(ApresentacaoDto apresentacaoDto) throws Exception{
        Apresentacao apresentacaoSalva = null;
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Apresentacao apresentacao = Apresentacao.builder()
                .musica(apresentacaoDto.getMusica())
                .nomeartistico(apresentacaoDto.getNomeartistico())
                .tom(apresentacaoDto.getTom())
                .gravacao(apresentacaoDto.getGravacao())
                .autor(apresentacaoDto.getAutor())
                .linkmusica(apresentacaoDto.getLinkmusica())
                .individuos(apresentacaoDto.getIndividuos())
                .categoria(categoriaServices.encontrarPorId(apresentacaoDto.getCategoria()).get())
                .build();
                apresentacaoSalva = this.apresentacaoDB.save(apresentacao);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
        return apresentacaoSalva.getId();
    }


    public ApresentacaoDto atualizar(ApresentacaoDto apresentacaoDto, long id){
        long categoria = 0;
        try {
            switch (apresentacaoDto.getCategoriaTitulo().toLowerCase()) {
                case "infantil":
                    categoria = 1L;
                    break;
                case "juvenil":
                    categoria = 2L;
                    break;
                case "popular":
                    categoria = 3L;
                    break;
                case "sertaneja":
                    categoria = 4L;
                    break;                                    
            
                default:
                    break;
            }
            // Encontra objeto salvo pelo id e depois atualiza
            Apresentacao apresentacao = this.encontrarPorId(id).get();

            // Atualizar modelo com campos do objeto dto
            apresentacao.setMusica(apresentacaoDto.getMusica());
            apresentacao.setNomeartistico(apresentacaoDto.getNomeartistico());
            apresentacao.setTom(apresentacaoDto.getTom());
            apresentacao.setGravacao(apresentacaoDto.getGravacao());
            apresentacao.setAutor(apresentacaoDto.getAutor());
            apresentacao.setIndividuos(apresentacaoDto.getIndividuos());
            // Verifica se deve alterar categoria
            if((apresentacaoDto.getCategoria() != null && apresentacaoDto.getCategoria() > 0) || (categoria > 0 && categoria <= 4))
                apresentacao.setCategoria(categoriaServices.encontrarPorId(categoria).get());
            // Verificações se deve alterar ordem e/ou senha
            if(apresentacaoDto.getOrdem() > 0)
                apresentacao.setOrdem(apresentacaoDto.getOrdem());
            if(apresentacaoDto.getSenha() > 0)
                apresentacao.setSenha(apresentacaoDto.getSenha());

            this.apresentacaoDB.save(apresentacao);
            return apresentacaoDto;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<String> remover(long id){
        try {
            
            Apresentacao apresentacao = this.encontrarPorId(id).get();
            this.apresentacaoDB.delete(apresentacao);

            return ResponseEntity.ok().body("Removido objeto de id: "+id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    public List<ListaCartaoApresentacaoDto> encontrarCartao(){
        List<ListaCartaoApresentacaoDto> listaDto = new ArrayList<ListaCartaoApresentacaoDto>();
        Pageable pageable = PageRequest.of(0, 50, Sort.by("ordem"));
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        Page<Map<String, Object>> resultados = apresentacaoDB.findAllCartao(pageable);
        for (Map<String, Object> row : resultados.getContent()) {
            Long codigo = (Long) row.get("codigo");
            String musica = (String) row.get("musica");
            String nomeartistico = (String) row.get("nomeartistico");
            String autor = (String) row.get("autor");
            Integer ordem = (row.get("ordem") != null) ? (Integer) row.get("ordem") : 0;
            String categoriaTitulo = (String) row.get("categoriaTitulo");
            byte[] fotoPerfil = (byte[]) row.get("fotoPerfil");

            listaDto.add(new ListaCartaoApresentacaoDto(codigo, musica, nomeartistico, autor, ordem, categoriaTitulo, fotoPerfil));
        }
        
        return listaDto;
    }

    public List<ListaCartaoApresentacaoDto> encontrarFiltradoCartao(FiltroApresentacaoDto filtro){
        List<ListaCartaoApresentacaoDto> listaDto = new ArrayList<ListaCartaoApresentacaoDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 100, Sort.by(filtro.getOrdem()));
        List<Apresentacao> listaFiltrada = apresentacaoDB.
            findAllFiltrado(
                filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();        
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: listaFiltrada) {
            listaDto.add(new ListaCartaoApresentacaoDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), 
                apresentacao.getAutor(), apresentacao.getOrdem(), apresentacao.getCategoria().getTitulo(),
                null));
        }

        return listaDto;
    }  
    
}
