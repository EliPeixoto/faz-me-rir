package br.com.fazmerir.controller;


import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.entities.Despesa;
import br.com.fazmerir.enums.StatusDespesaEnum;
import br.com.fazmerir.response.DespesaResponse;
import br.com.fazmerir.services.DespesaService;
import br.com.fazmerir.validation.ValidaDespesa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/despesas")
public class DespesaController {

    private final DespesaService service;
    private final ValidaDespesa validaDespesa;



    @GetMapping
    public ResponseEntity<List<DespesaDto>> listarComFiltro(
            @RequestParam(required = false) BigDecimal valorDespesa,
            @RequestParam(required = false) String descricaoDespesa,
            @RequestParam(required = false) StatusDespesaEnum statusDespesa
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarDespesasComFiltro(valorDespesa, descricaoDespesa, statusDespesa));
    }

    @GetMapping("somar-filtro")
    public ResponseEntity<DespesaResponse> somarComFiltro(
            @RequestParam(required = false) BigDecimal valorDespesa,
            @RequestParam(required = false) String descricaoDespesa,
            @RequestParam(required = false) StatusDespesaEnum statusDespesa
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.somarDespesasComFiltro(valorDespesa, descricaoDespesa, statusDespesa));
    }

    @PostMapping
    public ResponseEntity<Despesa> cadastraDespesa(@RequestBody DespesaDto dto) {
        validaDespesa.validaDespesaDescricao(dto);
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrarDespesa(dto));
    }


    @PutMapping("/altera-status/{id}")
    public ResponseEntity<DespesaDto> alteraStatusDespesa(@PathVariable Long id) {
        DespesaDto statusAtualizado = service.alteraStatusDespesa(id);
        return ResponseEntity.status(HttpStatus.OK).body(statusAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaDespesa(@PathVariable Long id){
         service.deletaDespesa(id);
         return ResponseEntity.ok("Despesa deletada com sucesso");
    }


}
