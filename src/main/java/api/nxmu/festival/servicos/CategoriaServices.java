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
public class CategoriaServices {

    private final CategoriaRepositorio categoriaDB;

    public Optional<Categoria> encontrarPorId(Long id){        
        return categoriaDB.findById(id);
    }

    public List<CategoriaDto> encontrar(){
        List<CategoriaDto> listaDto = new ArrayList<CategoriaDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Categoria categoria: categoriaDB.findAll()) {
            listaDto.add(new CategoriaDto(
                categoria.getTitulo(), categoria.getDescricao(), 
                categoria.getDataInicial(), categoria.getDataFinal()));
        }

        return listaDto;
    }

    public boolean salvar(CategoriaDto categoria){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Categoria e = new Categoria(
                categoria.getTitulo(), categoria.getDescricao(), 
                categoria.getDataInicial(), categoria.getDataFinal());

            this.categoriaDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
