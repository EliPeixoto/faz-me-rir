package br.com.fazmerir.entities;


import br.com.fazmerir.enums.StatusDespesaEnum;
import br.com.fazmerir.util.AuditListener;
import br.com.fazmerir.util.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditListener.class)
public class Despesa implements AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private BigDecimal valorDespesa;
    private String descricaoDespesa;
    private StatusDespesaEnum statusDespesa;

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
