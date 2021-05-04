package org.tms.application.services;

import org.tms.application.dto.CarDto;
import org.tms.domain.service.CarSalesService;
import reactor.core.publisher.Mono;

public interface RegisterCarService extends CarSalesService<CarDto, Mono<CarDto>> {
}
