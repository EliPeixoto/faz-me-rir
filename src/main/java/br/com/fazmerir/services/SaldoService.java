package br.com.fazmerir.services;


import br.com.fazmerir.dto.SaldoDto;
import br.com.fazmerir.dto.SaldoResponseDto;
import br.com.fazmerir.entities.Saldo;
import br.com.fazmerir.mapper.SaldoMapper;
import br.com.fazmerir.repository.SaldoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class SaldoService {


    private final SaldoRepository repository;
    private final SaldoMapper mapper;
    private final DespesaService despesaService;
    private final ReceitaService receitaService;

    public SaldoResponseDto saldoAtual() {
        BigDecimal totalReceita = receitaService.somarReceitaPorStatus();
        BigDecimal totalDespesa = despesaService.somarDespesaPorStatus();
        BigDecimal saldo = totalReceita.subtract(totalDespesa);
        LocalDate horaAtual = LocalDate.now();

        SaldoResponseDto saldoTotal = new SaldoResponseDto();
        saldoTotal.setValorTotal(saldo);
        saldoTotal.setAtualizadoEm(horaAtual);

        return saldoTotal;
    }


    public SaldoDto cadastrarSaldo(SaldoDto dto) {
        Saldo valor = mapper.toEntity(dto);
        return mapper.toDto(repository.save(valor));
    }

}
