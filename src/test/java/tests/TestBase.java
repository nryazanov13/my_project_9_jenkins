package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.Attach;
import helpers.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Map;

public class TestBase {

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @BeforeAll
    static void setUpAll() {

        Configuration.browser = getProperty("browser", "chrome");
        Configuration.browserSize = getProperty("browserSize", "1920x1080");

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        String remoteHost = System.getProperty("remoteHost");
        if (remoteHost != null && !remoteHost.isEmpty()) {
            String login = config.login();
            String password = config.password();
            Configuration.remote = String.format("https://%s:%s@%s/wd/hub", login, password, remoteHost);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }

    // Вспомогательный метод для получения свойств с дефолтными значениями
    private static String getProperty(String name, String defaultValue) {
        String property = System.getProperty(name);
        return (property != null && !property.isEmpty()) ? property : defaultValue;
    }
}