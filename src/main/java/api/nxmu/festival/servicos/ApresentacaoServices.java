package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.ApresentacaoDto;
import api.nxmu.festival.modelo.Apresentacao;
import api.nxmu.festival.repositorio.ApresentacaoRepositorio;

@Service
public class ApresentacaoServices {

    @Autowired
    private ApresentacaoRepositorio apresentacaoDB;

    public Optional<Apresentacao> encontrarPorId(Long id){        
        return apresentacaoDB.findById(id);
    }

    public List<ApresentacaoDto> encontrar(){
        List<ApresentacaoDto> listaDto = new ArrayList<ApresentacaoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Apresentacao apresentacao: apresentacaoDB.findAll()) {
            listaDto.add(new ApresentacaoDto(
                apresentacao.getId(), apresentacao.getMusica(), apresentacao.getTom(), 
                apresentacao.getAutor(), apresentacao.getGravacao(), apresentacao.getIndividuos(), 
                1, 1));
        }

        return listaDto;
    }

    public boolean salvar(ApresentacaoDto apresentacaoDto){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Apresentacao e = new Apresentacao(
                apresentacaoDto.getCodigo(), apresentacaoDto.getMusica(), apresentacaoDto.getTom(), apresentacaoDto.getGravacao(), 
                apresentacaoDto.getAutor(), apresentacaoDto.getIndividuos(), 
                apresentacaoDto.getParticipante(), apresentacaoDto.getCategoria());

            this.apresentacaoDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
