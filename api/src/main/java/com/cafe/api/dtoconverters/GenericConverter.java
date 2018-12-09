package com.cafe.api.dtoconverters;


import com.cafe.dto.AbstractDto;
import com.cafe.model.GenericEntity;
import org.springframework.stereotype.Component;

public interface GenericConverter <E extends GenericEntity, D extends AbstractDto> {

    E toModel(D dto);

    D toDto(E entity);

    E updateEntity(E entity, D dto);

//    default List createFromEntities(final Collection entities) {
//        return entities.stream()
//                .map(this::createFrom)
//                .collect(Collectors.toList());
//    }
//
//    default List createFromDtos(final Collection dtos) {
//        return dtos.stream()
//                .map(this::createFrom)
//                .collect(Collectors.toList());
//    }
}
