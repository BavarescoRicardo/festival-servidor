package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.CategoriaDto;
import api.nxmu.festival.modelo.Categoria;
import api.nxmu.festival.repositorio.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepositorio categoriaDB;

    public Optional<Categoria> encontrarPorId(Long id){        
        return categoriaDB.findById(id);
    }

    public List<CategoriaDto> encontrar(){
        List<CategoriaDto> listaDto = new ArrayList<CategoriaDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Categoria categoria: categoriaDB.findAll()) {
            listaDto.add(new CategoriaDto(
                categoria.getId(), categoria.getTitulo(), categoria.getDescricao(), 
                categoria.getDataInicial(), categoria.getDataFinal()));
        }

        return listaDto;
    }
    
    public List<CategoriaDto> encontrarAtivas(){
        List<CategoriaDto> listaDto = new ArrayList<CategoriaDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Categoria categoria: categoriaDB.findAllAtivos()) {
            listaDto.add(new CategoriaDto(
                categoria.getId(), categoria.getTitulo(), categoria.getDescricao(), 
                categoria.getDataInicial(), categoria.getDataFinal()));
        }

        return listaDto;
    }    

    public CategoriaDto salvar(CategoriaDto categoria){        
        try {
            Categoria e = new Categoria(
                    categoria.getTitulo(), categoria.getDescricao(), 
                    categoria.getDataInicial(), categoria.getDataFinal());

            this.categoriaDB.save(e);
            return categoria;    
        } catch (Exception e) {
            return null;
        }        
    }

    public CategoriaDto atualizar(CategoriaDto categoria, long id){
        try {
            // Encontra objeto salvo pelo id e depois atualiza
            Categoria cat = this.encontrarPorId(id).get();
            cat.setTitulo(categoria.getTitulo());
            cat.setDescricao(categoria.getDescricao());
            cat.setDataInicial(categoria.getDataInicial());
            cat.setDataFinal(categoria.getDataFinal());

            this.categoriaDB.save(cat);
            return categoria;    
        } catch (Exception e) {
            return null;
        }
    }
    
    public String remover(long id){
        try {
            Categoria cat = this.encontrarPorId(id).get();
            final String nomeCategoia = cat.getDescricao();
            this.categoriaDB.delete(cat);

            return nomeCategoia;
        } catch (Exception e) {
            return null;
        }
    }    
}
