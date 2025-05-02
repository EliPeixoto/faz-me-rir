package br.com.fazmerir.entities;

import br.com.fazmerir.enums.StatusReceitaEnum;
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
public class Receita {

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
    private StatusReceitaEnum statusReceita;

}
