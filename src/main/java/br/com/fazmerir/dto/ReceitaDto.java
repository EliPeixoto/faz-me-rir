package br.com.fazmerir.dto;

import br.com.fazmerir.enums.StatusReceitaEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaDto(

        Long id,

        @NotNull
        @Positive
        BigDecimal valorReceita,

        @NotNull
        LocalDate dataRecebimento,

        @Size(min = 3, max = 255)
        String descricaoRecebimento,

        String categoriaReceita,

        @Enumerated
        StatusReceitaEnum statusReceita
) {
}
