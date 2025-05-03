package br.com.fazmerir.mapper;

import br.com.fazmerir.dto.SaldoDto;
import br.com.fazmerir.entities.Saldo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaldoMapper {

    Saldo toEntity(SaldoDto dto);

    SaldoDto toDto(Saldo entity);
}
