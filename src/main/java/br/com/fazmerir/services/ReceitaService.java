package br.com.fazmerir.services;

import br.com.fazmerir.dto.ReceitaDto;
import br.com.fazmerir.entities.Receita;
import br.com.fazmerir.enums.StatusReceitaEnum;
import br.com.fazmerir.mapper.ReceitaMapper;
import br.com.fazmerir.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReceitaService {

    private final ReceitaMapper mapper;
    private final ReceitaRepository receitaRepository;


    public ResponseEntity<List<ReceitaDto>> listarReceitas() {

        List<Receita> receitas = receitaRepository.findAll();
        return ResponseEntity.ok(mapper.toDto(receitas));
    }


    public ReceitaDto buscaReceitaPorId(Long id){

        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada com id: " + id));

        return mapper.toDto(receita);
    }

    public ReceitaDto cadastrarReceita(ReceitaDto receitaDto) {

        Receita receita = mapper.toEntity(receitaDto);


        receitaRepository.save(receita);


        return mapper.toDto(receita);
    }

    public ReceitaDto atualizarReceita(Long id, ReceitaDto receitaDto) {
        Receita receitaExistente = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        receitaExistente.setValorReceita(receitaDto.valorReceita());
        receitaExistente.setDescricaoRecebimento(receitaDto.descricaoRecebimento());
        receitaExistente.setCategoriaReceita(receitaDto.categoriaReceita());

        receitaRepository.save(receitaExistente);
        return mapper.toDto(receitaExistente);
    }


    public ReceitaDto alteraStatusReceita(Long id){
        Receita receitaExistente = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        StatusReceitaEnum novoStatus = receitaExistente.getStatusReceita() == StatusReceitaEnum.RECEBIDO
                ? StatusReceitaEnum.PENDENTE
                : StatusReceitaEnum.RECEBIDO;

        receitaExistente.setStatusReceita(novoStatus);

        receitaRepository.save(receitaExistente);
        return  mapper.toDto(receitaExistente);

    }

    public BigDecimal somarReceitaPorStatus() {

       List<Receita> receitaExistente = receitaRepository.findAll();

       return receitaExistente.stream()
               .filter(r -> StatusReceitaEnum.RECEBIDO.equals(r.getStatusReceita()))
               .map(Receita::getValorReceita)
               .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public void deletarReceita(Long id){
        receitaRepository.deleteById(id);
    }
}
