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
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditListener.class)
public class Despesa implements AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "despesa_seq")
    @SequenceGenerator(name = "despesa_seq", sequenceName = "despesa_id_seq", allocationSize = 1)
    private Long id;

    private BigDecimal valorDespesa;
    private String descricaoDespesa;
    @Enumerated(EnumType.STRING)
    private StatusDespesaEnum statusDespesa;
    private LocalDate dataVencimento;
    @ManyToOne(optional = false)
    @JoinColumn(name = "CATEGORIA_ID")
    private Categoria categoria;


    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
