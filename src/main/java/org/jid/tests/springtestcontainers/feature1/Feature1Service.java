package org.jid.tests.springtestcontainers.feature1;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class Feature1Service {

    private Feature1Repo repo;

    public Feature1Service(Feature1Repo repo) {
        this.repo = repo;
    }

    public void newFeature1(String text) {
        repo.save(new Feature1Model(null, text, Instant.now()));
    }

    public List<Feature1Model> getAll() {
        return repo.findAll();
    }
}
