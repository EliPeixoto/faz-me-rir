package br.com.fazmerir.controller;


import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.entities.Despesa;
import br.com.fazmerir.enums.StatusDespesaEnum;
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
    public ResponseEntity<List<DespesaDto>> listarComFiltro(
            @RequestParam(required = false) BigDecimal valorDespesa,
            @RequestParam(required = false) String descricaoDespesa,
            @RequestParam(required = false) StatusDespesaEnum statusDespesa
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarDespesasComFiltro(valorDespesa, descricaoDespesa, statusDespesa));
    }

    @GetMapping("somar-filtro")
    public ResponseEntity<BigDecimal> somarComFiltro(
            @RequestParam(required = false) BigDecimal valorDespesa,
            @RequestParam(required = false) String descricaoDespesa,
            @RequestParam(required = false) StatusDespesaEnum statusDespesa
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.somarDespesasComFiltro(valorDespesa, descricaoDespesa, statusDespesa));
    }

    @PostMapping
    public ResponseEntity<Despesa> cadastraDespesa(@RequestBody DespesaDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrarDespesa(dto));
    }

    @GetMapping("/soma-pago")
    public ResponseEntity<BigDecimal> somaDespesasPagas() {
        return ResponseEntity.status(HttpStatus.OK).body(service.somarDespesaPorStatus());
    }

    @PutMapping("/altera-status/{id}")
    public ResponseEntity<DespesaDto> alteraStatusDespesa(@PathVariable Long id) {
        DespesaDto statusAtualizado = service.alteraStatusDespesa(id);
        return ResponseEntity.status(HttpStatus.OK).body(statusAtualizado);
    }
}
