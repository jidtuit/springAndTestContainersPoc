package org.jid.tests.springtestcontainers.util;

import org.testcontainers.containers.GenericContainer;

import java.util.List;

public class CustomGenericTestContainer extends GenericContainer<CustomGenericTestContainer> {

    public CustomGenericTestContainer() {
        super("mongo:4.0.19-xenial");
        setExposedPorts(List.of(27017));
    }

    @Override
    public void start() {
        super.start();
        withExposedPorts(27017);
        System.setProperty("MONGODB_URI", "mongodb://" + getContainerIpAddress() + ":" + getFirstMappedPort());
    }
}
