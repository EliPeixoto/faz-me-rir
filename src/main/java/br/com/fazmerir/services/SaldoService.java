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
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SaldoService {


    private final SaldoRepository repository;
    private final SaldoMapper mapper;
    private final DespesaService despesaService;
    private final ReceitaService receitaService;

    public SaldoDto saldoAtual() {
        BigDecimal totalReceita = receitaService.somarReceitasStatusRecebido();
        BigDecimal totalDespesa = despesaService.somarDespesaPorStatusPago();
        BigDecimal saldo = totalReceita.subtract(totalDespesa);
        LocalDateTime agora = LocalDateTime.now();

        LocalDate hoje = agora.toLocalDate();
        LocalDateTime inicioDia = hoje.atStartOfDay();
        LocalDateTime fimDia = hoje.plusDays(1).atStartOfDay().minusNanos(1);

        Optional<Saldo> ultimoDoDiaOpt =
                repository.findTop1ByDataEntradaBetweenOrderByDataEntradaDesc(inicioDia, fimDia);

        Saldo entidadeParaSalvar;
        if (ultimoDoDiaOpt.isPresent()) {
            Saldo ultimoDoDia = ultimoDoDiaOpt.get();

            boolean mesmoValor = ultimoDoDia.getValor().compareTo(saldo) == 0;

            if (mesmoValor) {
                ultimoDoDia.setDataEntrada(agora);
                entidadeParaSalvar = ultimoDoDia;
            } else {
                entidadeParaSalvar = new Saldo();
                entidadeParaSalvar.setValor(saldo);
                entidadeParaSalvar.setDataEntrada(agora);
            }
        } else {
            entidadeParaSalvar = new Saldo();
            entidadeParaSalvar.setValor(saldo);
            entidadeParaSalvar.setDataEntrada(agora);
        }

        Saldo salvo = repository.save(entidadeParaSalvar);

        SaldoDto dto = new SaldoDto();
        dto.setValor(salvo.getValor());
        dto.setDataEntrada(salvo.getDataEntrada());

        return dto;
    }


    public SaldoDto cadastrarSaldo(SaldoDto dto) {
        Saldo valor = mapper.toEntity(dto);
        return mapper.toDto(repository.save(valor));
    }

}
