package br.com.fazmerir.mapper;

import br.com.fazmerir.dto.ReceitaDto;
import br.com.fazmerir.entities.Receita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {

    @Mapping(target = "valorReceita", source = "valorReceita")
    @Mapping(target = "dataRecebimento", source = "dataRecebimento")
    @Mapping(target = "descricaoRecebimento", source = "descricaoRecebimento")
    @Mapping(target = "categoriaReceita", source = "categoriaReceita")
    @Mapping(target = "statusReceita", source = "statusReceita")
    Receita toEntity(ReceitaDto dto);

    @Mapping(target = "valorReceita", source = "valorReceita")
    @Mapping(target = "dataRecebimento", source = "dataRecebimento")
    @Mapping(target = "descricaoRecebimento", source = "descricaoRecebimento")
    @Mapping(target = "categoriaReceita", source = "categoriaReceita")
    @Mapping(target = "statusReceita", source = "statusReceita")
    ReceitaDto toDto(Receita entity);

    List<ReceitaDto> toDto(List<Receita> entities);

    List<Receita> toEntity(List<ReceitaDto> dtos);

}
