package br.com.fazmerir.controller;

import br.com.fazmerir.dto.ReceitaDto;
import br.com.fazmerir.services.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
