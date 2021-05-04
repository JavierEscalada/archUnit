package org.tms.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.tms.application.dto.CarDto;
import org.tms.domain.model.Car;


@Mapper
public interface CarDtoToCarMapper {
    CarDtoToCarMapper mapperInstance = Mappers.getMapper(CarDtoToCarMapper.class);
    CarDto mapToCarDto(Car car);
	Car mapToCarDomain(CarDto policy);
}
