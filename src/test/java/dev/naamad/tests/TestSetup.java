package dev.naamad.tests;

import dev.naamad.factories.EnvironmentFactory;
import dev.naamad.factories.WebDriverFactory;
import dev.naamad.steps.BaseSteps;
import dev.naamad.utils.ApplicationProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Optional;


@Listeners({dev.naamad.listeners.Listeners.class})
public abstract class TestSetup {

    protected BaseSteps baseSteps;
    protected String baseUrl;
    protected String userName;
    protected String password;


    @BeforeMethod
    public void setup() {
        baseUrl = getPropertyOrDefault("baseUrl", "https://github.com/login");
        userName = getPropertyOrDefault("user", "coldblowlane7@gmail.com");
        password = getPropertyOrDefault("password", "testingpassword7!");
        baseSteps = EnvironmentFactory.getEnvironment(getPropertyOrDefault("environment", "local_chrome"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (baseSteps != null) this.baseSteps.tearDown();
    }

    public BaseSteps getBaseSteps() {
        return baseSteps;
    }

    private String getPropertyOrDefault(String propertyName, String defaultValue) {
        return Optional.ofNullable(System.getProperty(propertyName))
                .orElseGet(() -> Optional.ofNullable(new ApplicationProperties().readProperty(propertyName))
                        .orElseThrow());
    }

}
