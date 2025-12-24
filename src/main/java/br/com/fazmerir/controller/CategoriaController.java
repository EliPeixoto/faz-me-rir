package br.com.fazmerir.controller;

import br.com.fazmerir.dto.CategoriaRequest;
import br.com.fazmerir.dto.CategoriaResponse;
import br.com.fazmerir.entities.Categoria;
import br.com.fazmerir.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/categoria")
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;


    @PostMapping
    public ResponseEntity<Categoria>cadastraCategoria(@RequestBody CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaService.cadastraCategoria(categoriaRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }
}
