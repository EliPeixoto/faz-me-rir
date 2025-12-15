package br.com.fazmerir.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String descricao;

}
