package br.com.fazmerir.repository;

import br.com.fazmerir.entities.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Long> {


    Optional<Saldo> findTop1ByDataEntradaBetweenOrderByDataEntradaDesc(
            LocalDateTime inicio,
            LocalDateTime fim
    );

}
