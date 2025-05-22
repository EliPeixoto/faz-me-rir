package br.com.fazmerir.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UsuarioDto {
    private List<String> roles;
    private String name;
}
