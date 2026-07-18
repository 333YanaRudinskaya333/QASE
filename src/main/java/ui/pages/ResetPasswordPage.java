package ui.pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class ResetPasswordPage extends BasePage {
    private static final By RESET_PASSWORD_TITLE = By.xpath("//h1[text()='Reset your password']");
    private static final String EMAIL_FIELD = "[name='email']";
    public static final String NOTIFICATION_FOR_SUCCESSFUL_RESET = ("//span[contains(text(), 'We have e-mailed your password reset link!')]");
    private static final String ERROR_ALERT_FOR_INVALID_EMAIL = "[role='alert'] span";
    private static final String ERRORS_FOR_EMPTY_LOGIN_FIELD = "//small[contains(text(), 'This field is required')]";

    @Step("Открыть страницу восстановления пароля")
    public ResetPasswordPage openPage() {
        log.info("Opening Reset Password page");
        open(RESET_PASSWORD_PAGE);
        return this;
    }

    @Step("Запросить восстановление пароля для email: '{email}'")
    public LoginPage resetPassword(String email) {
        log.info("Attempting to reset password for email: '{}'", email);
        $(EMAIL_FIELD).setValue(email);
        log.info("Clicking the 'Send password reset link' button");
        $(byText(SEND_PASSWORD_RESET_LINK_BUTTON)).click();
        return new LoginPage();
    }

    public SelenideElement getNotificationForSuccessfulReset() {
        log.debug("Getting success notification element for password reset");
        return $x(NOTIFICATION_FOR_SUCCESSFUL_RESET);
    }

    @Step("Запросить восстановление пароля с некорректным email: '{invalidEmail}'")
    public ResetPasswordPage resetPasswordWithInvalidEmail(String invalidEmail) {
        log.info("Attempting password reset with invalid email format: '{}'", invalidEmail);
        $(EMAIL_FIELD).setValue(invalidEmail);
        log.info("Clicking the 'Send password reset link' button (negative test case)");
        $(byText(SEND_PASSWORD_RESET_LINK_BUTTON)).click();
        return this;
    }

        public SelenideElement getErrorNotification() {
            log.debug("Getting error notification element for invalid email");
        return $(ERROR_ALERT_FOR_INVALID_EMAIL);
    }

    public SelenideElement getErrorForEmptyEmail() {
        log.debug("Getting validation error element for empty email field");
        return $x(ERRORS_FOR_EMPTY_LOGIN_FIELD);
    }

    @Step("Нажать ссылку 'Log in' для возврата на страницу авторизации")
    public LoginPage clickLogIn(){
        log.info("Clicking the 'Log in' button to return to the Login page");
        $(byText(LOG_IN_BUTTON_ON_RESET_PASSWORD_PAGE)).click();
        return new LoginPage();
    }

    @Override
    @Step("Проверить, что страница восстановления пароля успешно открыта")
    public ResetPasswordPage isPageOpened() {
        log.debug("Checking if Reset Password page is opened");
        $(RESET_PASSWORD_TITLE).shouldBe(visible);
        log.info("Reset Password page is successfully opened");
        return this;
    }
}
