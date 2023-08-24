package api.nxmu.festival.servicos;

import java.util.List;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.nxmu.festival.dto.EnderecoDto;
import api.nxmu.festival.dto.ImportacaoDto;
import api.nxmu.festival.dto.ParticipanteDto;
import api.nxmu.festival.dto.ApresentacaoDto;
import api.nxmu.festival.modelo.Endereco;
import api.nxmu.festival.repositorio.EnderecoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoServices {

    private final EnderecoRepositorio enderecoDB;
    private final ParticipanteServices participanteServices;
    private final ApresentacaoServices apresentacaoServices;

    public Optional<Endereco> encontrarPorId(Long id){        
        return enderecoDB.findById(id);
    }

    public Optional<Endereco> encontrarPorParticipante(Long id){        
        try {
            return enderecoDB.findByIdParticipante(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
            
        }
    }

    public void removerPorParticipante(Long id){        
        try {
            enderecoDB.removeByIdParticipante(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    


    public List<EnderecoDto> encontrar(){
        List<EnderecoDto> listaDto = new ArrayList<EnderecoDto>();
        
        // Converte a lista de objetos da entidade em objetos dto para transferencia
        for(Endereco endereco: enderecoDB.findAll()) {
            listaDto.add(new EnderecoDto(
                endereco.getEndereco(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(),
                endereco.getTelefone(), endereco.getParticipante().getId()));
        }

        return listaDto;
    }

    public boolean salvar(EnderecoDto endereco){
        try {
            // Define objeto  participante para salvar no banco de dados a partir do dto recebido
            Endereco e = new Endereco(
                endereco.getEndereco(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(),
                endereco.getTelefone(), participanteServices.encontrarPorId(endereco.getParticipante()).get());

            this.enderecoDB.save(e);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public EnderecoDto atualizar(EnderecoDto endereco, long id){
        try {
            // Seleciona objeto salvo no banco pelo seu id e depois o atualiza com o dto
            Endereco e = this.encontrarPorId(id).get();
            e.setEndereco(endereco.getEndereco()); 
            e.setCidade(endereco.getCidade()); 
            e.setEstado(endereco.getEstado());
            e.setCep(endereco.getCep());
            e.setTelefone(endereco.getTelefone()); 
            e.setParticipante(participanteServices.encontrarPorId(endereco.getParticipante()).get());

            this.enderecoDB.save(e);    
        } catch (Exception e) {
            return null;
        }
        return endereco;
    }

    
    public ResponseEntity<String> remover(long id){
        try {
            // Encontra objetos da lista de participantes pelo id da apresentacao
            List<ParticipanteDto> participantes = participanteServices.encontrarPorApresentacaoId(id);
            // remover endereco de todos os participantes 
            for (ParticipanteDto participanteDto : participantes) {
                // enderecoDB.removeByIdParticipante(participanteDto.getCodigo());                 
                Endereco endereco = enderecoDB.findById(participanteDto.getCodigo()).get();
                enderecoDB.deleteById(endereco.getId());
                // remover participante 
                participanteServices.remover(participanteDto.getCodigo());
                apresentacaoServices.remover(id);
            }

            // por fim remover apresentacao
            apresentacaoServices.remover(id);
            

            return ResponseEntity.ok().body("Removido objeto de id: "+id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }    

    public boolean importar(){
        try {
            int qtde = 0;
            List<ImportacaoDto> importacoes = new ArrayList<ImportacaoDto>();
            try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/fimusii.csv"))) {
                String line;
                
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    qtde++;
                    if(qtde == 1)
                        continue;
                      importacoes.add(
                          new ImportacaoDto(
                             values[1], // Nome Artistico 1
                              values[2] + values[3], // Primeiro e ultimo nome
                              values[4],  // Data de nascimento
                              values[5], // Número do Documento
                              values[6], // email
                              values[7], // telefone
                              values[8], // necessidade espec - sim - não
                              values[10], // descrição' necessidade espec 
                                    // Repete para dupla
                              values[11] + values[12], // Primeiro e ultimo nome
                              values[13],  // Data de nascimento
                              values[14], // email
                              values[15], // telefone                              
                                    // Repete para trio
                              values[16] + values[17], // Primeiro e ultimo nome
                              values[18],  // email
                              values[19], // telefone
                              values[20], // Data de nascimento 
                                    // Repete pata grupo
                              values[21] + values[22], // Primeiro e ultimo nome
                              values[23],  // email
                              values[24], // telefone
                              values[25], // Data de nascimento 
                                    //  codigo apresentacao
                                Long.valueOf(0),
                                    // Dados Apresentação
                              values[31], // musica
                              values[33], // tom 
                              values[32], // gravação
                              values[34], // autor
                             1, // participação                              
                                    //  codigo apresentacao
                                Long.parseLong("1"), // categoria 
                                "", // dESCRIÇÃO CATEGORIA
                             // Dados endereco
                              values[26], // bairro
                              values[27], // cidade
                              values[28], // estado
                              values[29], // codigo postal
                                    //  codigo apresentacao
                                Long.valueOf(0)
                    //          // Dados Finais
                    //          values[38] // Documento foto
                                          )
                      );

                }
            }             

            
            System.out.println("Importando csv");
            for (ImportacaoDto importacao : importacoes) {                
                // Salvar a apresentacao de cada importacao
                long idApre = apresentacaoServices.salvar(
                    new ApresentacaoDto(
                        Long.valueOf(0),
                        importacao.getMusica(),
                        importacao.getNomeArtistico(),
                        importacao.getTom(),
                        importacao.getGravacao(),
                        importacao.getAutor(),
                        importacao.getOrdem(),
                        importacao.getSenha(),
                        importacao.getIndividuos(),
                        importacao.getCategoria()
                    )
                );            
                long idPart = participanteServices.salvar(
                    new ParticipanteDto(
                        Long.valueOf(0),
                        importacao.getNomeArtistico(), importacao.getNomeResponsavel(), 
                        importacao.getGenero(), importacao.getNascimento(),
                        importacao.getDocumentorg(), importacao.getEmail(), 
                        importacao.getNecessidade(), importacao.getDescrinescessidade(),
                        idApre
                    )
                );

                // Define objeto  participante para salvar no banco de dados a partir do dto recebido
                Endereco e = new Endereco(
                    importacao.getEndereco(), importacao.getCidade(), importacao.getEstado(), importacao.getCep(),
                    importacao.getTelefone(), participanteServices.encontrarPorId(idPart).get());

                this.enderecoDB.save(e);
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }    

}
