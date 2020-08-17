package org.jid.tests.springtestcontainers.util;

import org.testcontainers.containers.GenericContainer;

public class CustomGenericTestContainer extends GenericContainer<CustomGenericTestContainer> {

    public CustomGenericTestContainer() {
        super("mongo:4.0.19-xenial");
        withExposedPorts(27017);
        // An alternative method to withExposedPorts
        //setExposedPorts(List.of(27017));
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("MONGODB_URI", "mongodb://" + getContainerIpAddress() + ":" + getFirstMappedPort());
    }
}
