package br.com.fazmerir.entities;

import br.com.fazmerir.enums.StatusReceitaEnum;
import br.com.fazmerir.util.AuditListener;
import br.com.fazmerir.util.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "receita")
@EntityListeners(AuditListener.class)
public class Receita implements AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull
    private BigDecimal valorReceita;

    @NotNull
    private LocalDate dataRecebimento;

    @Size(min = 3, max = 255)
    private String descricaoRecebimento;

    private String categoriaReceita;

    @Enumerated
    @Column(nullable = false)
    private StatusReceitaEnum statusReceita;


    @Override
    public boolean isNew() {
        return false;
    }
}
