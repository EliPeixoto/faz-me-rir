package br.com.fazmerir.mapper;

import br.com.fazmerir.dto.ReceitaDto;
import br.com.fazmerir.entities.ReceitaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {

    ReceitaDto toDto(ReceitaEntity entity);
    ReceitaEntity toEntity(ReceitaDto dto);

    List<ReceitaDto> toDto(List<ReceitaEntity> entities);

    List<ReceitaEntity> toEntity(List<ReceitaDto> dtos);

}
