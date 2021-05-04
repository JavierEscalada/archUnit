package org.tms.adapters.rest;

import org.springframework.web.bind.annotation.*;
import org.tms.application.dto.CarDto;
import org.tms.application.services.RegisterCarService;
import org.tms.application.services.FindAllCarsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/policies")
public class CarSalesController {

    final RegisterCarService registerCarService;
    final FindAllCarsService findAllCarsSService;

    CarSalesController(final RegisterCarService registerCarService, final FindAllCarsService findAllCarsSService) {
        this.registerCarService = registerCarService;
        this.findAllCarsSService = findAllCarsSService;
    }

    @GetMapping("/")
    Flux<CarDto> getAllCars() {
        return findAllCarsSService.invoke(null);
    }

    @PostMapping("/")
    Mono<CarDto> registerCar(final @RequestBody CarDto carDto) {
        return registerCarService.invoke(carDto);
    }
}
