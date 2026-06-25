import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.util.HashMap;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    protected LoginPage loginPage;
    protected ProjectsPage projectsPage;
    protected ProjectPage projectPage;
    protected ResetPasswordPage resetPasswordPage;
    protected ProfileSettingsPage profilePage;

    @BeforeMethod(alwaysRun = true, description = "Настройка конфигурации и запуск браузера")
    public void setUp() {
        String browserName = System.getProperty("browser", "chrome");

        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        if (browserName.equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("prefs", prefs);
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-infobars");

            Configuration.browserCapabilities = chromeOptions;

        } else if (browserName.equalsIgnoreCase("edge")) {
            Configuration.browser = "edge";

            org.openqa.selenium.edge.EdgeOptions edgeOptions = new org.openqa.selenium.edge.EdgeOptions();
            edgeOptions.setExperimentalOption("prefs", prefs);
            edgeOptions.addArguments("--incognito");
            edgeOptions.addArguments("--disable-notifications");
            edgeOptions.addArguments("--disable-popup-blocking");
            edgeOptions.addArguments("--disable-infobars");

            Configuration.browserCapabilities = edgeOptions;

        } else {
            Configuration.browser = "chrome";

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("prefs", prefs);
            chromeOptions.addArguments("--incognito");
            Configuration.browserCapabilities = chromeOptions;
        }

        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
        projectPage = new ProjectPage();
        resetPasswordPage = new ResetPasswordPage();
        profilePage = new ProfileSettingsPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod
    public void tearDown() {
        getWebDriver().quit();
    }
}