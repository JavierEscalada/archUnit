package org.tms.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.tms.adapters.persistence.LegacyCarRepository;
import org.tms.adapters.persistence.documents.CarDocument;

import java.util.List;

@Deprecated
@Controller
public class LegacyCarSalesController {

    @Autowired
    private LegacyCarRepository carRepository;

    @GetMapping("/cars/")
    List<CarDocument> getAllPolicies() {
        return carRepository.findAll();
    }

    @PostMapping("/cars/")
    CarDocument registerCar(final @RequestBody CarDocument carDocument) {
        return carRepository.insert(carDocument);
    }

}
