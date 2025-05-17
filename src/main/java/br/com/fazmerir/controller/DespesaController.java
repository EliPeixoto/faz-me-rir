package br.com.fazmerir.controller;


import br.com.fazmerir.entities.Despesa;
import br.com.fazmerir.services.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/despesas")
public class DespesaController {

    private final DespesaService service;


    @GetMapping
    public ResponseEntity<List<Despesa>> listarDepesas(){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarDespesas());
    }
}
