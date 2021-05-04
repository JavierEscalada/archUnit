package org.tms.application.services;

import org.tms.application.dto.CarDto;
import org.tms.domain.service.CarSalesService;
import reactor.core.publisher.Flux;

public interface FindAllCarsService extends CarSalesService<Void,Flux<CarDto>> {
}
