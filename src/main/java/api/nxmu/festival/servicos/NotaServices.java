package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.NotaDto;
import api.nxmu.festival.dto.TabelaNotaDto;
import api.nxmu.festival.modelo.Nota;
import api.nxmu.festival.repositorio.NotaRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotaServices {

    private final NotaRepositorio notaDB;    
    private final CategoriaServices categoriaServices;
    private final JuradoServices juradoServices;    
    private final ApresentacaoServices apresentacaoServices;
    private final QuesitoServices quesitoServices;
    // QuesitoServices quesitoServices = new QuesitoServices();

    public Optional<Nota> encontrarPorId(Long id){        
        return notaDB.findById(id);
    }

    public List<TabelaNotaDto> encontrar(){
        List<TabelaNotaDto> listaDto = new ArrayList<TabelaNotaDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Nota nota: notaDB.findAll()) {
            listaDto.add(new TabelaNotaDto(
                nota.getNota(), nota.getCategoria().getDescricao(), 
                nota.getApresentacao().getNomeartistico(), nota.getJurado().getNome(), 
                nota.getApresentacao().getMusica(), nota.getQuesito().getDescricao()));
        }

        return listaDto;
    }

    public boolean salvar(NotaDto notaDto){
        try {

            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Nota e =  Nota.builder()
                .nota(notaDto.getNota())
                .categoria(categoriaServices.encontrarPorId(notaDto.getCategoria()).get())
                .jurado(juradoServices.encontrarPorId(notaDto.getJurado()).get())
                .apresentacao(apresentacaoServices.encontrarPorId(notaDto.getApresentacao()).get())
                .quesito(quesitoServices.encontrarPorId(notaDto.getQuesito())).build();
            this.notaDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
