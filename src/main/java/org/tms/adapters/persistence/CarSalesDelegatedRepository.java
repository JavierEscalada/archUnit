package org.tms.adapters.persistence;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.tms.adapters.persistence.documents.CarDocument;

@Repository
public interface CarSalesDelegatedRepository extends ReactiveMongoRepository<CarDocument, String> {
}
