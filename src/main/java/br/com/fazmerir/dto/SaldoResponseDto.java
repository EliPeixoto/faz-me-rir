package br.com.fazmerir.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaldoResponseDto (
        BigDecimal valorTotal,
        LocalDate atualizadoEm
){
}
