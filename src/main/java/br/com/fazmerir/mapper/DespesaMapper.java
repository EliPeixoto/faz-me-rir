package br.com.fazmerir.mapper;


import br.com.fazmerir.dto.DespesaDto;
import br.com.fazmerir.entities.Despesa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DespesaMapper {

    DespesaMapper INSTANCE = Mappers.getMapper(DespesaMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "categoria", ignore = true)
    Despesa toEntity(DespesaDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "categoria", source = "categoria.descricao")
    DespesaDto toDto(Despesa entity);


    List<DespesaDto> toListDto(List<Despesa> entity);

}
