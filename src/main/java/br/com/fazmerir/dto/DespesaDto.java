package br.com.fazmerir.dto;


import br.com.fazmerir.enums.StatusDespesaEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DespesaDto {

    private BigDecimal valorDespesa;
    private String descricaoDespesa;
    private StatusDespesaEnum statusDespesa;
    private LocalDate dataVencimento;
}
