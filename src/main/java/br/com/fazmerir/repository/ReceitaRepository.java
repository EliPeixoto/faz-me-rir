package br.com.fazmerir.repository;

import br.com.fazmerir.entities.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query("SELECT COALESCE(SUM(r.valorReceita), 0) FROM Receita r WHERE r.statusReceita = :status")
    BigDecimal somarReceitasPorStatus(@Param("status") br.com.fazmerir.enums.StatusReceitaEnum status);

    @Query("SELECT MAX(r.dataRecebimento) FROM Receita r WHERE r.statusReceita = :status")
    LocalDate buscarUltimaDataPorStatus(@Param("status") br.com.fazmerir.enums.StatusReceitaEnum status);


}
