package br.com.fazmerir.services;


import br.com.fazmerir.dto.SaldoDto;
import br.com.fazmerir.dto.SaldoResponseDto;
import br.com.fazmerir.entities.Saldo;
import br.com.fazmerir.mapper.SaldoMapper;
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

    public SaldoResponseDto saldoAtual() {
        BigDecimal total = repository.somarTodosOsSaldos();
        LocalDate ultimaAtualizacao = repository.buscarUltimaDataEntrada();
        return new SaldoResponseDto(total, ultimaAtualizacao);

    }

    public SaldoDto cadastrarSaldo(SaldoDto dto) {
        Saldo valor = mapper.toEntity(dto);
        return mapper.toDto(repository.save(valor));
    }

}
