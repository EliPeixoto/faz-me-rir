package br.com.fazmerir.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CATEGORIA")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
    @SequenceGenerator(
            name = "categoria_seq",
            sequenceName = "CATEGORIA_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID")
    Long id;

    @Column(name = "DESCRICAO", unique = true, nullable = false)
    private String descricao;

}
