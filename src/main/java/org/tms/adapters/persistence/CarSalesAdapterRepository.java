package org.tms.adapters.persistence;

import org.springframework.stereotype.Repository;
import org.tms.application.dto.CarDto;
import org.tms.application.ports.CarSalesPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.tms.adapters.persistence.mapper.CarDocumentToCarDtoMapper.mapperCarDocumentCarDto;

@Repository
public class CarSalesAdapterRepository implements CarSalesPort {

    private final CarSalesDelegatedRepository carSalesDelegatedRepository;

    public CarSalesAdapterRepository(final CarSalesDelegatedRepository carSalesDelegatedRepository) {
        this.carSalesDelegatedRepository = carSalesDelegatedRepository;
    }

    @Override
    public Mono<CarDto> save(final CarDto p) {
        return carSalesDelegatedRepository
            .save(mapperCarDocumentCarDto.mapToCarDocument(p))
            .map(mapperCarDocumentCarDto::mapToCarDto);
    }

    @Override
    public Flux<CarDto> findAll() {
        return carSalesDelegatedRepository.findAll().map(mapperCarDocumentCarDto::mapToCarDto);
    }
}
