package org.tms.domain.model;

import java.util.Objects;
import java.util.stream.Stream;

public class Car{

    private String licensePlate;
    private Double price;

    private Car() {
    }



    public static CarBuilder builder() {
        return new CarBuilder();
    }

    public static final class CarBuilder {
        private Car car;

        private CarBuilder() {
            car = new Car();
        }

        public CarBuilder licensePlate(final String licensePlate){
            car.licensePlate=licensePlate;
            return this;
        }

        public CarBuilder price(final Double price){
            car.price=price;
            return this;
        }


        public Car build() {
            validate();
            return car;
        }

        private void validate() {
            if (anyNull(car.licensePlate, car.price))
                throw new IncompleteCarException();
        }

        private boolean anyNull(Object... object) {
            return Stream.of(object).anyMatch(Objects::isNull);
        }
    }

    private static class IncompleteCarException extends IllegalArgumentException {
        public IncompleteCarException() {
            super("mandatory fields are required.");
        }
    }
}

