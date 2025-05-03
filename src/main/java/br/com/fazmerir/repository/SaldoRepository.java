package br.com.fazmerir.repository;

import br.com.fazmerir.entities.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Long> {


    @Query("SELECT COALESCE(SUM(s.valor), 0) FROM Saldo s")
    BigDecimal somarTodosOsSaldos();

    @Query("SELECT MAX(s.dataEntrada) FROM Saldo s")
    LocalDate buscarUltimaDataEntrada();
}
