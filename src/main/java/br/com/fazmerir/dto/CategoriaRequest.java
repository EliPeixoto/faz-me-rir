package br.com.fazmerir.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequest {
    private Long id;
    private String descricao;
}
