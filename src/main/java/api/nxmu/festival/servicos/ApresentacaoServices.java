package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ApresentacaoDto;
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
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: apresentacaoDB.findAll()) {
            listaDto.add(new ApresentacaoDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(), 
                apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getIndividuos(), 
                apresentacao.getCategoria().getTitulo()));
        }

        return listaDto;
    }

    public List<ApresentacaoDto> encontrarFiltrado(FiltroApresentacaoDto filtro){
        List<ApresentacaoDto> listaDto = new ArrayList<ApresentacaoDto>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 40);
        List<Apresentacao> listaFiltrada = apresentacaoDB.
            findAllFiltrado(
                filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();        
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: listaFiltrada) {
            listaDto.add(new ApresentacaoDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getNomeartistico(), apresentacao.getTom(), 
                apresentacao.getGravacao(), apresentacao.getAutor(), apresentacao.getIndividuos(), 
                apresentacao.getCategoria().getTitulo()));
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
                apresentacao.getCategoria().getTitulo()));
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

    public ApresentacaoDto atualizar(ApresentacaoDto apresentacaoDto, long id){
        try {
            // Encontra objeto salvo pelo id e depois atualiza
            Apresentacao apresentacao = this.encontrarPorId(id).get();

            // Atualizar modelo com campos do objeto dto
            apresentacao.setMusica(apresentacaoDto.getMusica());
            apresentacao.setNomeartistico(apresentacaoDto.getNomeartistico());
            apresentacao.setTom(apresentacaoDto.getTom());
            apresentacao.setGravacao(apresentacaoDto.getGravacao());
            apresentacao.setAutor(apresentacaoDto.getAutor());
            apresentacao.setIndividuos(apresentacaoDto.getIndividuos());
            apresentacao.setCategoria(categoriaServices.encontrarPorId(apresentacaoDto.getCategoria()).get());
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
    
}
