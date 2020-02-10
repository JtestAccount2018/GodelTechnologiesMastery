package com.godel.mastery.functionaltest;

import com.godel.mastery.functionaltest.support.DatabaseClient;
import com.godel.mastery.functionaltest.support.TestContext;
import org.jbehave.core.annotations.BeforeScenario;
import org.springframework.web.client.RestTemplate;

import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;

public class AbstractSteps {

    protected static final String APP_HOST = ofNullable(getenv("HOST")).orElse("localhost");
    protected static final String APP_PORT = "8080";
    protected static final String APP_PATH = "employees";
    protected static final String BASE_URL = "http://" + APP_HOST + ":" + APP_PORT + "/" + APP_PATH;
    protected static final RestTemplate REST_TEMPLATE = new RestTemplate();

    protected final TestContext<EmployeeServiceKeys> context;

    public AbstractSteps(TestContext<EmployeeServiceKeys> context) {
        this.context = context;
    }

    private static String getEnvOrDefault(){
        return ofNullable(getenv("HOST")).orElse("localhost");
    }

    @BeforeScenario
    public void configure(){
        DatabaseClient.dropAllData();
        context.clear();
    }
}
