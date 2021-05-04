package org.tms.application.services;

import org.springframework.stereotype.Service;
import org.tms.application.dto.CarDto;
import org.tms.application.ports.CarSalesPort;
import reactor.core.publisher.Flux;

@Service
public class FindAllCarsUseCase implements FindAllCarsService {

    private final CarSalesPort carSalesPort;

    public FindAllCarsUseCase(final CarSalesPort carSalesPort) {
        this.carSalesPort = carSalesPort;
    }

    @Override
    public Flux<CarDto> invoke(Void unused) {
        return carSalesPort.findAll();
    }
}
