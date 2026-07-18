package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class LoginPage extends BasePage {
    private static final String USER_NAME = "[name=email]";
    private static final String PASSWORD = "[name=password]";
    private static final By LOGIN_TITLE = By.xpath("//h1[text()='Log in to your account']");
    private static final String ERRORS_FOR_EMPTY_USER_NAME_AND_PASSWORD = "//small[contains(text(), 'This field is required')]";

    @Step("Открыть страницу авторизации")
    public LoginPage openPage() {
        log.info("Opening login page");
        open(LOGIN_PAGE);
        return this;
    }

    private boolean isUserAlreadyAuthorized() {
        return WebDriverRunner.url().contains("/projects");
    }

    @Step("Выполнить вход в систему под пользователем: '{user}'")
    public ProjectsPage login(String user, String password) {
        log.info("Attempting full login flow for user: '{}'", user);
        if (isUserAlreadyAuthorized()) {
            log.info("User is already logged in (detected by URL), skipping credentials entry");
            return new ProjectsPage().isPageOpened();
        }
        log.debug("Accepting cookie consent banner if present");
        if ($(shadowCss("#accept", "#usercentrics-cmp-ui")).exists()) {
            $(shadowCss("#accept", "#usercentrics-cmp-ui")).click();
        }
        $(USER_NAME).val(user);
        $(PASSWORD).val(password);
        $(byText(SIGN_IN_BUTTON)).click();
        log.info("Login form submitted, verifying redirection to Projects page");
        return new ProjectsPage().isPageOpened();
    }

    public SelenideElement getErrorMessage() {
        log.debug("Getting error message element for invalid credentials");
        return $(byText(ERROR_MESSAGE_FOR_WRONG_USER_NAME_AND_PASSWORD));
    }

    public ElementsCollection getErrorsForEmptyUserNameAndPassword() {
        log.debug("Getting validation error elements for empty username and password fields");
        return $$x(ERRORS_FOR_EMPTY_USER_NAME_AND_PASSWORD);
    }

    @Step("Ввести логин: '{user}' и пароль: '*******'")
    public LoginPage enterCredentials(String user, String password) {
        log.info("Entering credentials for user: '{}' (password: *******)", user);
        $(USER_NAME).val(user);
        $(PASSWORD).val(password);
        return this;
    }

    @Step("Нажать кнопку 'Sign In'")
    public LoginPage clickSignInButton() {
        log.info("Clicking the Sign In button");
        $(byText(SIGN_IN_BUTTON)).click();
        return this;
    }

    @Override
    @Step("Проверить, что страница авторизации успешно открыта")
    public LoginPage isPageOpened() {
        log.debug("Checking if Login page is opened");
        if (isUserAlreadyAuthorized()) {
            log.info("Login page redirect skipped: user is already authorized (detected by URL)");
            return this;
        }
        $(LOGIN_TITLE).shouldBe(visible);
        log.info("Login page is successfully opened");
        return this;
    }
}
