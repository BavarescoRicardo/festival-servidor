package api.nxmu.festival.servicos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ApresentacaoDto;
import api.nxmu.festival.dto.ApresentacaoRelDto;
import api.nxmu.festival.dto.ListaCartaoApresentacaoDto;
import api.nxmu.festival.dto.MusicaDto;
import api.nxmu.festival.dto.filtros.FiltroApresentacaoDto;
import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.projections.ApresentacaoProjection;
import api.nxmu.festival.repositorio.ApresentacaoRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApresentacaoService {

    private final ApresentacaoRepositorio apresentacaoDB;
    private final CategoriaService categoriaService;

    private static ApresentacaoDto getApresentacaoDto(Apresentacao apresentacao) {
        return new ApresentacaoDto(
                apresentacao.getId(),
                apresentacao.getMusica(),
                apresentacao.getNomeartistico(),
                apresentacao.getTom(),
                apresentacao.getGravacao(),
                apresentacao.getAutor(),
                apresentacao.getLinkmusica(),
                apresentacao.getParticipantes().get(0).getEnderecos().get(0).getCidade(),
                apresentacao.getParticipantes().get(0).getEnderecos().get(0).getId(),
                apresentacao.getOrdem(),
                apresentacao.getSenha(),
                apresentacao.getCategoria().getTitulo()
        );
    }
    public Optional<Apresentacao> encontrarPorId(Long id) {
        return apresentacaoDB.findById(id);
    }

    public ApresentacaoDto encontrarDtoPorId(Long id) {
        ApresentacaoDto dto;
        Apresentacao apresentacao = apresentacaoDB.findById(id).get();

        return getApresentacaoDto(apresentacao);
    }

    public List<ApresentacaoDto> encontrar() {
        List<ApresentacaoDto> listaDto = new ArrayList<ApresentacaoDto>();
        Pageable pageable = PageRequest.of(0, 50, Sort.by("ordem"));
        Page<Apresentacao> apresentacoes = apresentacaoDB.findAllOrdenado(pageable);

        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for (Apresentacao apresentacao : apresentacoes) {
        	if (apresentacao.getParticipantes().isEmpty()) {
        		continue;
        	}        	

            listaDto.add(
                    getApresentacaoDto(apresentacao)
            );
        }

        return listaDto;
    }

    public List<ApresentacaoDto> encontrarFiltrado(FiltroApresentacaoDto filtro) {
        List<ApresentacaoDto> listaDto = new ArrayList<ApresentacaoDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 80, Sort.by(filtro.getOrdem()));
        List<Apresentacao> listaFiltrada = apresentacaoDB.
                findAllFiltrado(
                        filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();
        for (Apresentacao apresentacao : listaFiltrada) {
        	if (apresentacao.getParticipantes().isEmpty()) {
        		continue;
        	}
            listaDto.add(
                    getApresentacaoDto(apresentacao)
            );
        }

        return listaDto;
    }

    public List<ApresentacaoRelDto> encontrarRel(FiltroApresentacaoDto filtro) {
        List<ApresentacaoRelDto> listaDto = new ArrayList<ApresentacaoRelDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 80, Sort.by(filtro.getOrdem()));
        List<Apresentacao> listaFiltrada = null;
        if (filtro.getTextoFiltro().length() > 1) { // findAllFiltradoEnsaio
            listaFiltrada = apresentacaoDB.findAllFiltradoEnsaio(
                    filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();
        } else {
            listaFiltrada = apresentacaoDB.findAllFiltrado(
                    filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();
        }

        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for (Apresentacao apresentacao : listaFiltrada) {
            listaDto.add(new ApresentacaoRelDto(
                    apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(),
                    apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getIndividuos(),
                    apresentacao.getOrdem(), apresentacao.getSenha(), apresentacao.getCategoria().getTitulo(),
                    apresentacao.getParticipantes().get(0).getEnderecos().get(0).getCidade()));
        }

        return listaDto;
    }

    public List<ApresentacaoRelDto> encontrarRelBanda(FiltroApresentacaoDto filtro) {
        List<ApresentacaoRelDto> listaDto = new ArrayList<ApresentacaoRelDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 80, Sort.by(filtro.getOrdem()));
        List<Apresentacao> listaFiltrada = apresentacaoDB.
                findAllFiltrado(
                        filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();

        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for (Apresentacao apresentacao : listaFiltrada) {
            listaDto.add(new ApresentacaoRelDto(
                    apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(),
                    apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getLinkmusica(), apresentacao.getIndividuos(),
                    apresentacao.getOrdem(), apresentacao.getSenha(), apresentacao.getCategoria().getTitulo(), "cidade"));
        }

        return listaDto;
    }

    public List<ApresentacaoDto> encontrarDtoPorCategoria(long codCategoria) {
        List<ApresentacaoDto> listaDto = new ArrayList<ApresentacaoDto>();

        for (Apresentacao apresentacao : apresentacaoDB.findAllByCategoria(codCategoria)) {
            listaDto.add(new ApresentacaoDto(
                    apresentacao.getId(),
                    apresentacao.getMusica(),
                    apresentacao.getNomeartistico(),
                    apresentacao.getTom(),
                    apresentacao.getGravacao(),
                    apresentacao.getAutor(),
                    apresentacao.getParticipantes().get(0).getEnderecos().get(0).getCidade(),
                    apresentacao.getParticipantes().get(0).getEnderecos().get(0).getId(),
                    apresentacao.getOrdem(),
                    apresentacao.getSenha(),
                    apresentacao.getCategoria().getTitulo()
            ));
        }

        return listaDto;
    }
    
    public List<Apresentacao> encontrarPorCategoria(long codCategoria) {
    	List<Apresentacao> apresentacoes = null;
    	try {
    		apresentacoes = apresentacaoDB.findAllByCategoria(codCategoria);
		} catch (Exception e) {
			return null;
		}
    	
    	return apresentacoes;
    }    

    @Transactional
    public Long salvar(ApresentacaoDto apresentacaoDto) throws Exception {
        try {
            Apresentacao apresentacao = criarApresentacao(apresentacaoDto);
            apresentacaoDB.save(apresentacao);
            return apresentacao.getId();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }



    private Apresentacao criarApresentacao(ApresentacaoDto apresentacaoDto) {
        Apresentacao apresentacao = Apresentacao.builder()
                .musica(apresentacaoDto.getMusica())
                .nomeartistico(apresentacaoDto.getNomeartistico())
                .tom(apresentacaoDto.getTom())
                .gravacao(apresentacaoDto.getGravacao())
                .autor(apresentacaoDto.getAutor())
                .linkmusica(apresentacaoDto.getLinkmusica())
                .individuos(1)
                .categoria(categoriaService.encontrarPorId(apresentacaoDto.getCategoria()).get())
                .build();
        return apresentacao;
    }

    public Long salvarImport(ApresentacaoDto apresentacaoDto) throws Exception {
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
                    // .individuos(apresentacaoDto.getIndividuos())
                    .categoria(categoriaService.encontrarPorId(apresentacaoDto.getCategoria()).get())
                    .build();
            apresentacaoSalva = this.apresentacaoDB.save(apresentacao);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
        return apresentacaoSalva.getId();
    }


    public ApresentacaoDto atualizar(ApresentacaoDto apresentacaoDto, long id) {
        long categoria = 0;
        try {
            switch (apresentacaoDto.getCategoriaTitulo().toLowerCase()) {
                case "infantil":
                    categoria = 1L;
                    break;
                case "juvenil":
                    categoria = 52L;
                    break;
                case "popular":
                    categoria = 2L;
                    break;
                case "sertaneja":
                    categoria = 53L;
                    break;
                case "gospel":
                    categoria = 50L;
                    break;
                case "final popular":
                    categoria = 6L;
                    break;
                case "final sertaneja":
                    categoria = 7L;
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
            apresentacao.setLinkmusica(apresentacaoDto.getLinkmusica());
            // Verifica se deve alterar categoria
            if ((apresentacaoDto.getCategoria() != null && apresentacaoDto.getCategoria() > 0) || (categoria > 0 && categoria <= 7))
                apresentacao.setCategoria(categoriaService.encontrarPorId(categoria).get());
            // Verificações se deve alterar ordem e/ou senha
            if (apresentacaoDto.getOrdem() > 0)
                apresentacao.setOrdem(apresentacaoDto.getOrdem());
            if (apresentacaoDto.getSenha() > 0)
                apresentacao.setSenha(apresentacaoDto.getSenha());

            this.apresentacaoDB.save(apresentacao);
            return apresentacaoDto;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<String> remover(long id) {
        try {

            Apresentacao apresentacao = this.encontrarPorId(id).get();
            apresentacao.setAtivo(1);
            this.apresentacaoDB.save(apresentacao);

            return ResponseEntity.ok().body("Removido objeto de id: " + id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    public List<ListaCartaoApresentacaoDto> encontrarCartao() {
        List<ListaCartaoApresentacaoDto> listaDto = new ArrayList<ListaCartaoApresentacaoDto>();
        Pageable pageable = PageRequest.of(0, 50, Sort.by("ordem"));

        // Converte a lista de objetos da entidade em objetos dto para transferencia
        Collection<ApresentacaoProjection> resultados = apresentacaoDB.findAllCartao(pageable);
        for (var apresentacaoProjection : resultados) {

            listaDto.add(new ListaCartaoApresentacaoDto(
                    apresentacaoProjection.getCodigo(),
                    apresentacaoProjection.getMusica(),
                    apresentacaoProjection.getNomeArtistico(),
                    apresentacaoProjection.getNomeAutor(),
                    apresentacaoProjection.getOrdem(),
                    apresentacaoProjection.getCategoriaTitulo(),
                    null));
        }

        return listaDto;
    }

    public List<ListaCartaoApresentacaoDto> encontrarFiltradoCartao(FiltroApresentacaoDto filtro) {
        List<ListaCartaoApresentacaoDto> listaDto = new ArrayList<ListaCartaoApresentacaoDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 100, Sort.by(filtro.getOrdem()));
        List<Apresentacao> listaFiltrada = apresentacaoDB.
                findAllFiltrado(
                        filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();

        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for (Apresentacao apresentacao : listaFiltrada) {
            listaDto.add(new ListaCartaoApresentacaoDto(
                    apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(),
                    apresentacao.getAutor(), apresentacao.getOrdem(), apresentacao.getCategoria().getTitulo(),
                    null));
        }

        return listaDto;
    }
    
    public List<MusicaDto> encontrarMusicaPorCategoria(String musica, long codCategoria) {
    	List<MusicaDto> listaMusicas = null;
    	try {
    		listaMusicas = apresentacaoDB.findByCategoriaAndMusica(musica, codCategoria);
		} catch (Exception e) {
			return null;
		}
    	
    	return listaMusicas;
    }     

}
