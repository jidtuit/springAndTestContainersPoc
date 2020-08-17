package org.jid.tests.springtestcontainers.util;

import org.testcontainers.containers.MongoDBContainer;

public class CustomMongoDbTestContainer extends MongoDBContainer {

    public CustomMongoDbTestContainer() {
        super("mongo:4.0.19-xenial");
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("MONGODB_URI", this.getReplicaSetUrl());
    }
}
