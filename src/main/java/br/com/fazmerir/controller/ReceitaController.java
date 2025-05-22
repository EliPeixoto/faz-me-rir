package br.com.fazmerir.controller;

import br.com.fazmerir.dto.ReceitaDto;
import br.com.fazmerir.services.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/receitas")
@CrossOrigin(origins = "http://localhost:4200")
public class ReceitaController {

    @Autowired
    private ReceitaService service;


    @GetMapping()
    public ResponseEntity<List<ReceitaDto>> listarReceitas() {
        return service.listarReceitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDto> buscaReceitaPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscaReceitaPorId(id));
    }

    @GetMapping("/soma-recebido")
    public ResponseEntity<BigDecimal> somarReceitasRecebidas(){
        return ResponseEntity.status(HttpStatus.OK).body(service.somarReceitaPorStatus());
    }

    @PostMapping
    public ResponseEntity<ReceitaDto> cadastrarReceita(@RequestBody @Valid ReceitaDto receitaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarReceita(receitaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDto> atualizarReceita(@PathVariable Long id, @RequestBody @Valid ReceitaDto receitaDto) {
        ReceitaDto receitaAtualizada = service.atualizarReceita(id, receitaDto);
        return ResponseEntity.ok(receitaAtualizada);
    }


    @PutMapping("altera-status/{id}")
    public ResponseEntity<ReceitaDto> alteraStatusReceita(@PathVariable Long id){
        ReceitaDto statusAtualizado = service.alteraStatusReceita(id);
        return ResponseEntity.ok(statusAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarReceita(@PathVariable  Long id){
        service.deletarReceita(id);
    }

}
