package br.com.fazmerir.services;

import br.com.fazmerir.dto.ReceitaDto;
import br.com.fazmerir.entities.Receita;
import br.com.fazmerir.enums.StatusReceitaEnum;
import br.com.fazmerir.mapper.ReceitaMapper;
import br.com.fazmerir.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaMapper mapper;

    @Autowired
    private ReceitaRepository repository;


    public ResponseEntity<List<ReceitaDto>> listarReceitas() {

        List<Receita> receitas = repository.findAll();
        return ResponseEntity.ok(mapper.toDto(receitas));
    }


    public ReceitaDto buscaReceitaPorId(Long id){

        Receita receita = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada com id: " + id));

        return mapper.toDto(receita);
    }

    public ReceitaDto cadastrarReceita(ReceitaDto receitaDto) {

        Receita receita = mapper.toEntity(receitaDto);
        return mapper.toDto(repository.save(receita));
    }

    public ReceitaDto atualizarReceita(Long id, ReceitaDto receitaDto) {
        Receita receitaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        receitaExistente.setValorReceita(receitaDto.valorReceita());
        receitaExistente.setDescricaoRecebimento(receitaDto.descricaoRecebimento());
        receitaExistente.setCategoriaReceita(receitaDto.categoriaReceita());

        repository.save(receitaExistente);
        return mapper.toDto(receitaExistente);
    }


    public ReceitaDto alteraStatusReceita(Long id){
        Receita receitaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        StatusReceitaEnum novoStatus = receitaExistente.getStatusReceita() == StatusReceitaEnum.RECEBIDO
                ? StatusReceitaEnum.PENDENTE
                : StatusReceitaEnum.RECEBIDO;

        receitaExistente.setStatusReceita(novoStatus);

        repository.save(receitaExistente);
        return  mapper.toDto(receitaExistente);

    }



    public void deletarReceita(Long id){
        repository.deleteById(id);
    }
}
