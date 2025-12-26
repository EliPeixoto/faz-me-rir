package br.com.fazmerir.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SaldoDto {

        BigDecimal valor;

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime dataEntrada;
}
