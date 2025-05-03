package br.com.fazmerir.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaldoDto(
        BigDecimal valor,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataEntrada

) {
}
