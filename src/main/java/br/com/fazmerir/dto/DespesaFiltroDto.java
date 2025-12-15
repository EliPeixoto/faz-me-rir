package br.com.fazmerir.dto;

import br.com.fazmerir.entities.Categoria;
import br.com.fazmerir.enums.StatusDespesaEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DespesaFiltroDto {
    private BigDecimal valorDespesa;
    private String descricaoDespesa;
    private StatusDespesaEnum statusDespesa;
    private Categoria categoria;
}
