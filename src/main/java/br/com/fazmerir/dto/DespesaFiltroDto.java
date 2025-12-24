package br.com.fazmerir.dto;

import br.com.fazmerir.entities.Categoria;
import br.com.fazmerir.enums.StatusDespesaEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DespesaFiltroDto {
    private BigDecimal valorDespesa;
    private String descricaoDespesa;
    private StatusDespesaEnum statusDespesa;
    private Categoria categoria;
    private LocalDate dataVencimento;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
}