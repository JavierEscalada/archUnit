package org.tms.adapters.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.tms.adapters.persistence.documents.CarDocument;
import org.tms.application.dto.CarDto;

@Mapper
public interface CarDocumentToCarDtoMapper {
    CarDocumentToCarDtoMapper mapperCarDocumentCarDto = Mappers.getMapper(CarDocumentToCarDtoMapper.class);
    CarDocument mapToCarDocument(CarDto policy);
    CarDto mapToCarDto(CarDocument policy);
}
