package org.tms.application.ports;

import org.tms.application.dto.CarDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarSalesPort {
    Mono<CarDto> save(CarDto car);
    Flux<CarDto>findAll();
}
