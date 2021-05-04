package org.tms.application.services;

import org.springframework.stereotype.Service;
import org.tms.application.dto.CarDto;
import org.tms.application.ports.CarSalesPort;
import reactor.core.publisher.Mono;

import static org.tms.application.mapper.CarDtoToCarMapper.mapperInstance;

@Service
public class RegisterCarUseCase implements RegisterCarService {

    private final CarSalesPort carSalesPort;

    public RegisterCarUseCase(final CarSalesPort carSalesPort) {
        this.carSalesPort = carSalesPort;
    }

    @Override
    public Mono<CarDto> invoke(final CarDto carDto) {
        return carSalesPort.save(mapperInstance.mapToCarDto(mapperInstance.mapToCarDomain(carDto)));
    }

}
