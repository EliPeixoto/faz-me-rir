package br.com.fazmerir.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "saldo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Saldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEntrada;
}
