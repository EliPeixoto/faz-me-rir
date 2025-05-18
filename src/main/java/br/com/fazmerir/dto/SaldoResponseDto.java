package br.com.fazmerir.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SaldoResponseDto{
    BigDecimal valorTotal;
    LocalDate atualizadoEm;
}
