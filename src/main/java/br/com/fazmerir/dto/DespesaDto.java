package br.com.fazmerir.dto;


import br.com.fazmerir.entities.Categoria;
import br.com.fazmerir.enums.StatusDespesaEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class DespesaDto {

    private Long id;
    private BigDecimal valorDespesa;
    private String descricaoDespesa;
    private StatusDespesaEnum statusDespesa;
    private LocalDate dataVencimento;
    private String numeroWhatsApp;
    private Categoria categoria;
}
