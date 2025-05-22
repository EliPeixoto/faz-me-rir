package br.com.fazmerir.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@MappedSuperclass
public class Auditoria {

    private LocalDateTime salvoEm;
    private String criadoPor;
    private LocalDateTime ultimaAlteracao;
    private String alteradoPor;


}
