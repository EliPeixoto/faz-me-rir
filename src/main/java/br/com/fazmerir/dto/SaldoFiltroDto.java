package br.com.fazmerir.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaldoFiltroDto {
    private boolean incluirSaldoManual;
    private boolean incluirReceitas;
}
