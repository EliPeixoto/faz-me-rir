package br.com.fazmerir.response;

import br.com.fazmerir.enums.StatusDespesaEnum;

import java.math.BigDecimal;

public class DespesaResponse {
    public String descricao;
    public StatusDespesaEnum status;
    public BigDecimal valor;

}
