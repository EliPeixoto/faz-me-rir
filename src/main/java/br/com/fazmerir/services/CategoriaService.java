package br.com.fazmerir.services;

import br.com.fazmerir.dto.CategoriaRequest;
import br.com.fazmerir.dto.CategoriaResponse;
import br.com.fazmerir.entities.Categoria;
import br.com.fazmerir.mapper.CategoriaMapper;
import br.com.fazmerir.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;


    public Categoria cadastraCategoria(CategoriaRequest request){

        Categoria categoria = mapper.toEntity(request);
        System.out.println(request);
        repository.save(categoria);

        return categoria;
    }

}
