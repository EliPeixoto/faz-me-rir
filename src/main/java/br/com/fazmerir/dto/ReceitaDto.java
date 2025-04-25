package br.com.fazmerir.dto;

import java.math.BigDecimal;

public record ReceitaDto(

        BigDecimal valorReceita,
        String descricaoRecebimento,
        String categoriaReceita
) {
}
