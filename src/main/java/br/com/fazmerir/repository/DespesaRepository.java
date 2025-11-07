package br.com.fazmerir.repository;

import br.com.fazmerir.entities.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DespesaRepository extends JpaRepository<Despesa, Long> , JpaSpecificationExecutor<Despesa> {


    boolean existsByDescricaoDespesa(String descricao);
}
