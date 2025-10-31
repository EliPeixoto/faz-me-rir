package br.com.fazmerir.mapper;


import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.entities.Despesa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DespesaMapper {

    DespesaMapper INSTANCE = Mappers.getMapper(DespesaMapper.class);

    @Mapping(source = "id", target = "id")
    Despesa toEntity(DespesaDto dto);

    @Mapping(source = "id", target = "id")
    DespesaDto toDto(Despesa entity);

}
