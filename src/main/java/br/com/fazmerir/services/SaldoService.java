package br.com.fazmerir.services;


import br.com.fazmerir.dto.SaldoDto;
import br.com.fazmerir.dto.SaldoFiltroDto;
import br.com.fazmerir.dto.SaldoResponseDto;
import br.com.fazmerir.dto.SaldoTotalResponseDto;
import br.com.fazmerir.entities.Saldo;
import br.com.fazmerir.enums.StatusReceitaEnum;
import br.com.fazmerir.mapper.SaldoMapper;
import br.com.fazmerir.repository.ReceitaRepository;
import br.com.fazmerir.repository.SaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SaldoService {

    @Autowired
    private SaldoRepository repository;

    @Autowired
    private SaldoMapper mapper;

    @Autowired
    private ReceitaRepository receitaRepository;

    public SaldoResponseDto saldoAtual() {
        BigDecimal total = receitaRepository.somarReceitasPorStatus(StatusReceitaEnum.RECEBIDO);
        LocalDate ultimaData = receitaRepository.buscarUltimaDataPorStatus(StatusReceitaEnum.RECEBIDO);
        return new SaldoResponseDto(total, ultimaData);
    }


    public SaldoDto cadastrarSaldo(SaldoDto dto) {
        Saldo valor = mapper.toEntity(dto);
        return mapper.toDto(repository.save(valor));
    }

    public SaldoDto cadastrarSaldoInicial(SaldoDto dto) {
        // Limpa qualquer saldo anterior (deixa apenas 1)
        repository.deleteAll();

        Saldo saldoInicial = mapper.toEntity(dto);
        return mapper.toDto(repository.save(saldoInicial));
    }

    public SaldoTotalResponseDto calcularSaldoTotal() {
        BigDecimal saldoInicial = repository.somarTodosOsSaldos(); // só terá 1 valor agora
        BigDecimal receitas = receitaRepository.somarReceitasPorStatus(StatusReceitaEnum.RECEBIDO);
        BigDecimal despesas = BigDecimal.ZERO; // futuramente: despesaRepository.somarDespesasPorStatus(StatusDespesaEnum.PAGO);

        return new SaldoTotalResponseDto(saldoInicial, receitas, despesas);
    }


    private LocalDate buscarUltimaData(SaldoFiltroDto filtro) {
        LocalDate dataSaldo = filtro.isIncluirSaldoManual() ? repository.buscarUltimaDataEntrada() : null;
        LocalDate dataReceita = filtro.isIncluirReceitas() ? repository.buscarUltimaDataReceita() : null;

        if (dataSaldo == null) return dataReceita;
        if (dataReceita == null) return dataSaldo;
        return dataSaldo.isAfter(dataReceita) ? dataSaldo : dataReceita;
    }


    public void adicionarSaldo(BigDecimal valor) {
        Saldo novoSaldo = new Saldo();
        novoSaldo.setValor(valor);
        novoSaldo.setDataEntrada(LocalDate.now());
        repository.save(novoSaldo);
    }
}
