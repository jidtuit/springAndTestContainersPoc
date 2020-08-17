package org.jid.tests.springtestcontainers.feature1;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Feature1Repo extends MongoRepository<Feature1Model, String> {
}
