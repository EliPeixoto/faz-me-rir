package br.com.fazmerir.controller;

import br.com.fazmerir.dto.ReceitaDto;
import br.com.fazmerir.services.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas")
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

    @PostMapping
    public ResponseEntity<ReceitaDto> cadastrarReceita(@RequestBody @Valid ReceitaDto receitaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarReceita(receitaDto));
    }


}
