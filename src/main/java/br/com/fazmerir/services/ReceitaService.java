package br.com.fazmerir.services;

import br.com.fazmerir.dto.ReceitaDto;
import br.com.fazmerir.entities.ReceitaEntity;
import br.com.fazmerir.mapper.ReceitaMapper;
import br.com.fazmerir.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private  ReceitaMapper mapper;

    @Autowired
    private ReceitaRepository repository;


    public ResponseEntity<List<ReceitaDto>> listarReceitas() {

        List<ReceitaEntity> receitas = repository.findAll();
        return ResponseEntity.ok(mapper.toDto(receitas));
    }



}
