package api.nxmu.festival.servicos;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.nxmu.festival.dto.ApresentacaoDto;
import api.nxmu.festival.dto.AtualizaClassificacaoDto;
import api.nxmu.festival.dto.ClassificacaoDto;
import api.nxmu.festival.dto.ClassificacaoListaDto;
import api.nxmu.festival.dto.ClassificacaoRelDto;
import api.nxmu.festival.dto.filtros.FiltroClassificacaoDto;
import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.modelo.Classificacao;
import api.nxmu.festival.modelo.NotaFinal;
import api.nxmu.festival.repositorio.ClassificacaoRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassificacaoService {

    @Autowired
    private ClassificacaoRepositorio classificacaoDB;
    
    @PersistenceContext
    private EntityManager entityManager;

    private final CategoriaService categoriaService;
    private final ApresentacaoService apresentacaoService;
    private final NotaFinalService notaFinalService;

    public Optional<Classificacao> encontrarPorId(Long id){        
        return classificacaoDB.findById(id);
    }

    public Optional<Classificacao> encontrarPorApresentacao(long id){        
        return classificacaoDB.findByIdApresentacao(id);
    }

    @Transactional
    public List<ClassificacaoListaDto> encontrar() {
        List<Classificacao> classificacoes = classificacaoDB.findAll();
        List<ClassificacaoListaDto> listaDto = new ArrayList<>();

        for (Classificacao classificacao : classificacoes) {
            // Check if apresentacao is not null before accessing its properties
            if (classificacao.getApresentacao() != null) {
                BigDecimal bd = new BigDecimal(classificacao.getNotafinal()).setScale(2, RoundingMode.HALF_DOWN);
                listaDto.add(new ClassificacaoListaDto(
                    classificacao.getId(),
                    bd.doubleValue(),
                    classificacao.getCategoria().getDescricao(),
                    classificacao.getApresentacao().getMusica(),
                    classificacao.getApresentacao().getNomeartistico()
                ));
            } else {
                // Optionally handle cases where apresentacao is null
                System.out.println("Classificacao with id " + classificacao.getId() + " has a null apresentacao");
            }
        }

        return listaDto;
    }

    public List<ClassificacaoListaDto> encontrarFiltrado(FiltroClassificacaoDto filtro){
        List<ClassificacaoListaDto> listaDto = new ArrayList<>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 10, Sort.by("notafinal").descending());
        List<Classificacao> listaFiltrada = classificacaoDB.
            findAllFiltrado(filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();
        
        for(Classificacao classificacao: listaFiltrada) {
            BigDecimal bd = new BigDecimal(classificacao.getNotafinal()).setScale(2, RoundingMode.HALF_DOWN);
            listaDto.add(new ClassificacaoListaDto(
                classificacao.getId(), bd.doubleValue(), classificacao.getCategoria().getDescricao(), 
                classificacao.getApresentacao().getMusica(), classificacao.getApresentacao().getNomeartistico()));
        }
        return listaDto;
    } 

    public List<ClassificacaoRelDto> encontrarRel(FiltroClassificacaoDto filtro){
        List<ClassificacaoRelDto> listaDto = new ArrayList<>();
        Pageable pageable = PageRequest.of(Integer.parseInt(filtro.getPg()), 100);
        List<Classificacao> listaFiltrada = classificacaoDB.findAllFiltrado(
                filtro.getCodCategoria(), filtro.getTextoFiltro(), pageable).getContent();        
        
        for(Classificacao classificacao: listaFiltrada) {
            BigDecimal bd = new BigDecimal(classificacao.getNotafinal()).setScale(2, RoundingMode.HALF_DOWN);
            listaDto.add(new ClassificacaoRelDto(
                classificacao.getId(), bd.doubleValue(), classificacao.getCategoria().getDescricao(), 
                classificacao.getApresentacao().getMusica(), classificacao.getApresentacao().getNomeartistico(), "Cidade x"));
        }
        return listaDto;
    }     

    public boolean salvar(ClassificacaoDto classificacao){
        try {
            Classificacao e = new Classificacao(
                classificacao.getNotafinal(), 
                categoriaService.encontrarPorId(classificacao.getCategoria()).get(),
                apresentacaoService.encontrarPorId(classificacao.getApresentacao()).get());
            classificacaoDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public AtualizaClassificacaoDto atualizar(AtualizaClassificacaoDto classificacao, long id){
        try {
            Classificacao clas = this.encontrarPorId(id).orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
            clas.setNotafinal(classificacao.getNotafinal());
            classificacaoDB.save(clas);
            return classificacao;
        } catch (Exception e) {
            return null;
        }
    }
    
    public ResponseEntity<String> remover(long id){
        try {
            Classificacao clas = this.encontrarPorId(id).orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
            classificacaoDB.delete(clas);
            return ResponseEntity.ok().body("Removido objeto de id: "+id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }   

    @Transactional
    public void calcularClassificacao(long codigoCategoria) {
        List<Apresentacao> apresentacoes = apresentacaoService.encontrarPorCategoria(codigoCategoria);
        if (apresentacoes.isEmpty())
            return;

        double media = 0;

        for (Apresentacao apresentacao : apresentacoes) {
            List<NotaFinal> notasApresentacao = notaFinalService.encontrarPorApresentacao(apresentacao.getId());

            if (notasApresentacao.isEmpty())
                continue;

            for (NotaFinal notaApresentacao : notasApresentacao) {
                media += notaApresentacao.getNotaFinal();
            }

            media = (media / notasApresentacao.size());
            Classificacao classificacao = new Classificacao(media, apresentacao.getCategoria(), apresentacao);

            Optional<Classificacao> existingClassificacao = this.encontrarPorApresentacao(apresentacao.getId());
            if(!existingClassificacao.isEmpty()) {
            	classificacao.setId(existingClassificacao.get().getId());
            }

            classificacaoDB.save(classificacao);
            media = 0;
        }
    }
}
