package dev.naamad.steps;

import dev.naamad.enums.Environment;
import dev.naamad.utils.ApplicationProperties;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirefoxSteps extends BaseSteps{

    public FirefoxSteps(Environment environment) {
        super(environment);
    }

    @Override
    protected MutableCapabilities initCapabilities() {
        return null;
    }
}
