package org.tms.adapters.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tms.adapters.persistence.documents.CarDocument;

@Deprecated
@Repository
public interface LegacyCarRepository extends MongoRepository<CarDocument,String> {
}
