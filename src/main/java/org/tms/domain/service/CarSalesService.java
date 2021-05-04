package org.tms.domain.service;

public interface CarSalesService<I,O> {
    O invoke(I i);
}
