package br.com.fazmerir.repository;

import br.com.fazmerir.entities.Receita;
import br.com.fazmerir.enums.StatusReceitaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}
