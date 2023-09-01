package api.nxmu.festival.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.nxmu.festival.dto.ApresentacaoDto;
import api.nxmu.festival.dto.ApresentacaoRelDto;
import api.nxmu.festival.dto.ListaCartaoApresentacaoDto;
import api.nxmu.festival.dto.filtros.FiltroApresentacaoDto;
import api.nxmu.festival.servicos.ApresentacaoServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class ApresentacaoController {

    @Autowired
    private final ApresentacaoServices apresentacaoService;

    @RequestMapping(value = "/apresentacoes", method =  RequestMethod.GET)
    public List<ApresentacaoDto> getApresentacoes(){
        return apresentacaoService.encontrar();
    }

    @RequestMapping(value = "/apresentacoesrel", method =  RequestMethod.POST)
    public List<ApresentacaoRelDto> getApresentacoesRel(
        @RequestBody(required = false) Optional<FiltroApresentacaoDto> filtro){
            FiltroApresentacaoDto filtroRecebido = filtro.get();
            return apresentacaoService.encontrarRel(filtroRecebido);
    }    

    @RequestMapping(value = "/apresentacoesrelbanda", method =  RequestMethod.POST)
    public List<ApresentacaoRelDto> getApresentacoesRelBanda(
        @RequestBody(required = false) Optional<FiltroApresentacaoDto> filtro){
            FiltroApresentacaoDto filtroRecebido = filtro.get();
            return apresentacaoService.encontrarRelBanda(filtroRecebido);
    }    


    @RequestMapping(value = "/apresentacoesfiltro", method =  RequestMethod.POST)
    public List<ApresentacaoDto> GetArtigoFiltrado(
    @RequestBody(required = false) Optional<FiltroApresentacaoDto> filtro) {        

        FiltroApresentacaoDto filtroRecebido = filtro.get();
        return apresentacaoService.encontrarFiltrado(filtroRecebido);
    }    

    @RequestMapping(value = "/salvaapresentacao", method =  RequestMethod.POST)
    public ResponseEntity<Long> salvarParticipante(@RequestBody ApresentacaoDto apresentacaoDto) {
        try {
            return ResponseEntity.ok(apresentacaoService.salvar(apresentacaoDto));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/apresentacao/{id}", method =  RequestMethod.PATCH)
	public ApresentacaoDto atualizarApresentacao(@RequestBody ApresentacaoDto apresentacao, @PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return apresentacaoService.atualizar(apresentacao, id);
        } catch (Exception e) {
            return null;
        }               
	}
    
    @RequestMapping(value = "/removeapresentacao/{id}", method =  RequestMethod.DELETE)
	public ResponseEntity<String> removeApresentacao(@PathVariable long id)
    {
        //  envolver metodo em try catch retorno certo no tr retorno false no catch
        try {
            return apresentacaoService.remover(id);
        } catch (Exception e) {
            return null;
        }
	}  

    @RequestMapping(value = "/apresentacoescartao", method =  RequestMethod.GET)
    public List<ListaCartaoApresentacaoDto> getApresentacoesCartao(){
        return apresentacaoService.encontrarCartao();
    }

    @RequestMapping(value = "/apresentacoesfiltrocartao", method =  RequestMethod.POST)
    public List<ListaCartaoApresentacaoDto> GetArtigoFiltradoCartao(
    @RequestBody(required = false) Optional<FiltroApresentacaoDto> filtro) {        
        
        FiltroApresentacaoDto filtroRecebido = filtro.get();
        return apresentacaoService.encontrarFiltradoCartao(filtroRecebido);
    }   

}
