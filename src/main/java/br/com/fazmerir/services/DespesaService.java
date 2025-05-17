package br.com.fazmerir.services;

import br.com.fazmerir.entities.Despesa;
import br.com.fazmerir.entities.Receita;
import br.com.fazmerir.repository.DespesaRepository;
import br.com.fazmerir.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository repository;

    public List<Despesa> buscarDespesas(){
        return repository.findAll();
    }
}
