package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.BrowserStackConfig;
import drivers.BrowserstackDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    private static final BrowserStackConfig config =
            ConfigFactory.create(
                    BrowserStackConfig.class,
                    System.getProperties()
            );

    @BeforeAll
    static void beforeAll() {
        WebDriverRunner.setWebDriver(new BrowserstackDriver(config).getWebDriver());
        Configuration.browserSize = null;
        Configuration.browser = BrowserstackDriver.class.getName();

    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();
        System.out.println(sessionId);
        Attach.pageSource();
        closeWebDriver();
        Attach.addVideo(sessionId);
    }
}