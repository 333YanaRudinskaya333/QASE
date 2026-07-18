package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import tests.listeners.TestListener;
import ui.pages.*;
import ui.steps.LoginStep;
import ui.steps.ProjectStep;
import ui.steps.SuiteStep;
import ui.steps.TestPlanStep;

import java.util.HashMap;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    protected LoginPage loginPage;
    protected ProjectsPage projectsPage;
    protected ProjectPage projectPage;
    protected ResetPasswordPage resetPasswordPage;
    protected ProfileSettingsPage profilePage;
    protected CreateTestCasePage createTestCasePage;
    protected TrashBinPage trashBinPage;
    protected TestPlansPage testPlanPage;
    protected CreateTestPlanPage createTestPlanPage;
    protected LoginStep loginStep;
    protected ProjectStep projectStep;
    protected SuiteStep suiteStep;
    protected TestPlanStep testPlanStep;

    @BeforeMethod(alwaysRun = true, description = "Настройка конфигурации и запуск браузера")
    public void setUp(ITestContext iTestContext) {
        String browserName = System.getProperty("browser", "chrome");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = isHeadless;
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

            if (isHeadless) {
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
            }

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
        createTestCasePage = new CreateTestCasePage();
        trashBinPage = new TrashBinPage();
        testPlanPage = new TestPlansPage();
        createTestPlanPage = new CreateTestPlanPage();
        loginStep = new LoginStep();
        projectStep = new ProjectStep();
        suiteStep = new SuiteStep();
        testPlanStep = new TestPlanStep();

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
