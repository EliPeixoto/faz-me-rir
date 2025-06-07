package br.com.fazmerir.mapper;


import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.entities.Despesa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DespesaMapper {

    DespesaMapper INSTANCE = Mappers.getMapper(DespesaMapper.class);

Despesa toEntity(DespesaDto dto);
DespesaDto toDto(Despesa entity);

}
