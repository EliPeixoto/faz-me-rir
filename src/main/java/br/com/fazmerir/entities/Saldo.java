package br.com.fazmerir.entities;

import br.com.fazmerir.util.AuditListener;
import br.com.fazmerir.util.AuditableEntity;
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
@EntityListeners(AuditListener.class)
public class Saldo implements AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor; // Agora representa o saldo inicial

    private LocalDate dataEntrada;

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
