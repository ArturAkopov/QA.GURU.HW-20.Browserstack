package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserStackConfig;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    private final BrowserStackConfig config;

    public BrowserstackDriver(BrowserStackConfig config) {
        this.config = config;
    }


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return getDriver();
    }

    public WebDriver getDriver() {
        MutableCapabilities caps = new MutableCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", config.browserstackUser());
        caps.setCapability("browserstack.key", config.browserstackPass());

        // Set URL of the application under test
        caps.setCapability("app", config.browserstackApp());

        // Specify device and os_version for testing
        caps.setCapability("device", config.browserstackDevice());
        caps.setCapability("os_version", config.browserstackPlatform());

        // Set other BrowserStack capabilities
        caps.setCapability("project", config.browserstackProject());
        caps.setCapability("build", config.browserstackBuild());
        caps.setCapability("name", config.browserstackName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(config.browserstackUrl()), caps);
        } catch (
                MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}