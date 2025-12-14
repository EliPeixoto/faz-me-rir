package br.com.fazmerir.filter;

import br.com.fazmerir.dto.DespesaFiltroDto;
import br.com.fazmerir.entities.Despesa;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public final class DespesaFilter {

    // Construtor privado para evitar instanciamento
    private DespesaFilter() {
        throw new UnsupportedOperationException("Classe utilitária, não deve ser instanciada.");
    }

    public static Specification<Despesa> despesaComFiltros(DespesaFiltroDto filtro) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getDescricaoDespesa() != null && !filtro.getDescricaoDespesa().isEmpty()) {
                predicates.add(builder.like(
                        builder.lower(root.get("descricaoDespesa")),
                         filtro.getDescricaoDespesa().toLowerCase() + "%"
                ));
            }

            if (filtro.getValorDespesa() != null) {
                predicates.add(builder.equal(root.get("valorDespesa"), filtro.getValorDespesa()));
            }

            if (filtro.getStatusDespesa() != null) {
                predicates.add(builder.equal(root.get("statusDespesa"), filtro.getStatusDespesa()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

