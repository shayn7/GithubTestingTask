package dev.naamad.steps;

import dev.naamad.enums.Environment;
import dev.naamad.utils.ApplicationProperties;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Optional;

public class ChromeSteps extends BaseSteps {


    public ChromeSteps(Environment environment) {
        super(environment);
    }

    @Override
    protected MutableCapabilities initCapabilities() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Optional<String> running_mode = Optional.ofNullable(System.getProperty("running_mode"));
        running_mode.ifPresent(e-> {
            if(e.equals("--headless")) chromeOptions.addArguments("--headless");
        });
        return chromeOptions;
    }

}
