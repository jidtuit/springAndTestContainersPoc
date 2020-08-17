package org.jid.tests.springtestcontainers.feature1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Feature1Controller {

    private Feature1Service service;

    public Feature1Controller(Feature1Service service) {
        this.service = service;
    }

    @GetMapping("/feature1")
    public List<Feature1Model> getAllFeatureList() {
        return service.getAll();
    }

    // Is a get for testing purposes but it should be a POST and no "create" in the url
    @GetMapping("/feature1/create/{text}")
    public String createText(@PathVariable String text) {
        service.newFeature1(text);
        return text;
    }

}
