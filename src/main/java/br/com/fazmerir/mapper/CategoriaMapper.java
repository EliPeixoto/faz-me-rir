package br.com.fazmerir.mapper;

import br.com.fazmerir.dto.CategoriaRequest;
import br.com.fazmerir.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    Categoria toEntity(CategoriaRequest dto);

    CategoriaRequest toDto(Categoria categoria);

}
