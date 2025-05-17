package br.com.fazmerir.services;

import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.entities.Despesa;
import br.com.fazmerir.entities.Receita;
import br.com.fazmerir.enums.StatusDespesaEnum;
import br.com.fazmerir.enums.StatusReceitaEnum;
import br.com.fazmerir.mapper.DespesaMapper;
import br.com.fazmerir.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository repository;
    private final DespesaMapper mapper;
    public List<Despesa> buscarDespesas(){
        return repository.findAll();
    }

    public Despesa cadastrarDespesa(DespesaDto dto) {
       Despesa despesa = mapper.toEntity(dto);
       return repository.save(despesa);
    }





    public BigDecimal somarDespesaPorStatus() {
        List<Despesa> despesaExistente = repository.findAll();

        BigDecimal totalPago = despesaExistente.stream()
                .filter(d -> StatusDespesaEnum.PAGO.equals(d.getStatusDespesa()))
                .map(Despesa::getValorDespesa)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPago;
    }
}
