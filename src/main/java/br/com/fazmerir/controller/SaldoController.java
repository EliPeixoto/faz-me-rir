package br.com.fazmerir.controller;

import br.com.fazmerir.dto.SaldoDto;
import br.com.fazmerir.dto.SaldoResponseDto;
import br.com.fazmerir.services.SaldoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/saldo")
public class SaldoController {

    @Autowired
    private SaldoService service;

    @GetMapping
    public ResponseEntity<SaldoResponseDto> saldoAtual(){
        return ResponseEntity.ok(service.saldoAtual());
    }

    @PostMapping
    public ResponseEntity<SaldoDto> cadastrarSaldo(@RequestBody @Valid SaldoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarSaldo(dto));
    }


}
