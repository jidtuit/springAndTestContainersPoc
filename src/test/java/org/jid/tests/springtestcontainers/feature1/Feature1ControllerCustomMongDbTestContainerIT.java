package org.jid.tests.springtestcontainers.feature1;

import org.assertj.core.api.Assertions;
import org.jid.tests.springtestcontainers.util.CustomMongoDbTestContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// IMPORTANT: Web environment MUST be RANDOM_PORT!!
@SpringBootTest(classes = Feature1ControllerCustomMongDbTestContainerIT.App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationtest")
@AutoConfigureMockMvc
@Testcontainers
class Feature1ControllerCustomMongDbTestContainerIT {

    @Container
    static CustomMongoDbTestContainer mongodb = new CustomMongoDbTestContainer();

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;

    @Test
    void storeAndRetrieveAllData() throws Exception {

        mvc.perform(get("/feature1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        String msg = "HolaMundo";
        mvc.perform(get("/feature1/create/{text}", msg))
                .andExpect(status().isOk())
                .andExpect(content().string(msg));

        mvc.perform(get("/feature1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(h -> Assertions.assertThat(h.getResponse().getContentAsString()).contains(msg));

    }

    @SpringBootApplication
    static class App {
        public static void main(String[] args) {
            SpringApplication.run(App.class, args);
        }
    }
}