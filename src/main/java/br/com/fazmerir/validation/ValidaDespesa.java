package br.com.fazmerir.validation;

import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.exceptions.DuplicateResourceException;
import br.com.fazmerir.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidaDespesa {

    private final DespesaRepository despesaRepository;


    public void validaDespesaDescricao(DespesaDto despesa){
        String desc = despesa.getDescricaoDespesa();
        if (desc == null || desc.isBlank()) return;

         boolean existe = despesaRepository.existsByDescricaoDespesa(desc.trim());

        if(existe){
           throw new DuplicateResourceException("Ja tem uma despesa com essa descricao");
        }
    }


    private boolean temDespesaComDescricao(String descricao){
        return despesaRepository.existsByDescricaoDespesa(descricao);
    }

}
