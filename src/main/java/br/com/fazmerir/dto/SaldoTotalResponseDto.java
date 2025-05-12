package br.com.fazmerir.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaldoTotalResponseDto {
    private BigDecimal saldoInicial;
    private BigDecimal totalReceitas;
    private BigDecimal totalDespesas;
    private BigDecimal saldoTotal;

    public SaldoTotalResponseDto(BigDecimal saldoInicial, BigDecimal totalReceitas, BigDecimal totalDespesas) {
        this.saldoInicial = saldoInicial;
        this.totalReceitas = totalReceitas;
        this.totalDespesas = totalDespesas;
        this.saldoTotal = saldoInicial.add(totalReceitas).subtract(totalDespesas);
    }

}
