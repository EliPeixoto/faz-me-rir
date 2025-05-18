package br.com.fazmerir.controller;


import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.entities.Despesa;
import br.com.fazmerir.services.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PostMapping
    public ResponseEntity<Despesa>cadastraDespesa(@RequestBody DespesaDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrarDespesa(dto));
    }

    @GetMapping("/soma-pago")
    public ResponseEntity<BigDecimal>somaDespesasPagas(){
        return ResponseEntity.status(HttpStatus.OK).body(service.somarDespesaPorStatus());
    }

    @PutMapping("/altera-status/{id}")
    public ResponseEntity<DespesaDto>alteraStatusDespesa(@PathVariable Long id){
        DespesaDto statusAtualizado = service.alteraStatusDespesa(id);
        return ResponseEntity.status(HttpStatus.OK).body(statusAtualizado);
    }
}
