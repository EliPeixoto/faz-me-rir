package br.com.fazmerir.mapper;

import br.com.fazmerir.dto.ReceitaDto;
import br.com.fazmerir.entities.ReceitaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {

    @Mapping(target = "valorReceita", source = "valorReceita")
    @Mapping(target = "dataRecebimento", source = "dataRecebimento")
    @Mapping(target = "descricaoRecebimento", source = "descricaoRecebimento")
    @Mapping(target = "categoriaReceita", source = "categoriaReceita")
    ReceitaEntity toEntity(ReceitaDto dto);

    @Mapping(target = "valorReceita", source = "valorReceita")
    @Mapping(target = "dataRecebimento", source = "dataRecebimento")
    @Mapping(target = "descricaoRecebimento", source = "descricaoRecebimento")
    @Mapping(target = "categoriaReceita", source = "categoriaReceita")
    ReceitaDto toDto(ReceitaEntity entity);

    List<ReceitaDto> toDto(List<ReceitaEntity> entities);

    List<ReceitaEntity> toEntity(List<ReceitaDto> dtos);

}
