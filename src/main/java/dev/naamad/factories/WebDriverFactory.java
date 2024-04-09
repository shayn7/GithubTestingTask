package dev.naamad.factories;

import dev.naamad.enums.Environment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import static dev.naamad.enums.Environment.LOCAL_CHROME;

public class WebDriverFactory {

    /**
     * Get a WebDriver instance based on the environment.
     *
     * @param environment The environment to use.
     * @param options     Additional options for configuring the driver.
     * @return A WebDriver instance.
     */

    public static synchronized WebDriver getDriver(Environment environment, MutableCapabilities options){
        if (environment == LOCAL_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = (ChromeOptions) options;
            return createLocalChromeDriver(chromeOptions);
        }
        throw new IllegalArgumentException("Unsupported environment: " + environment);
    }



    /**
     * Get a remote WebDriver instance.
     *
     * @param remoteUrl The URL of the remote WebDriver server.
     * @param options   Additional options for configuring the driver.
     * @return A remote WebDriver instance.
     * @throws MalformedURLException If the URL is not valid.
     */
    public static synchronized WebDriver getRemoteDriver(String remoteUrl, MutableCapabilities options) throws MalformedURLException {
        URL url = new URL(remoteUrl);
        return new RemoteWebDriver(url, options);
    }

    private static WebDriver createLocalChromeDriver(ChromeOptions chromeOptions) {
        return new ChromeDriver(chromeOptions);
    }
}

