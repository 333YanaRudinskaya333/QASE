package pages;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static dict.Elements.*;

public class ResetPasswordPage extends BasePage {
    private static final By RESET_PASSWORD_TITLE = By.xpath("//h1[text()='Reset your password']");
    private static final String EMAIL_FIELD = "[name='email']";
    public static final String NOTIFICATION_FOR_SUCCESSFUL_RESET = ("//span[contains(text(), 'We have e-mailed your password reset link!')]");
    private static final String ERROR_ALERT_FOR_INVALID_EMAIL = "[role='alert'] span";
    private static final String ERRORS_FOR_EMPTY_LOGIN_FIELD = "//small[contains(text(), 'This field is required')]";

    public ResetPasswordPage openPage() {
        open("/password/reset");
        return this;
    }

    public LoginPage resetPassword(String email) {
        $(EMAIL_FIELD).setValue(email);
        $(byText(SEND_PASSWORD_RESET_LINK_BUTTON)).click();
        return new LoginPage();
    }

    public SelenideElement getNotificationForSuccessfulReset() {
        return $x(NOTIFICATION_FOR_SUCCESSFUL_RESET);
    }

    public ResetPasswordPage resetPasswordWithInvalidEmail(String invalidEmail) {
        $(EMAIL_FIELD).setValue(invalidEmail);
        $(byText(SEND_PASSWORD_RESET_LINK_BUTTON)).click();
        return this;
    }

        public SelenideElement getErrorNotification() {
        return $(ERROR_ALERT_FOR_INVALID_EMAIL);
    }

    public SelenideElement getErrorForEmptyEmail() {
        return $x(ERRORS_FOR_EMPTY_LOGIN_FIELD);
    }

    public LoginPage clickLogIn(){
        $(byText(LOG_IN_BUTTON_ON_RESET_PASSWORD_PAGE)).click();
        return new LoginPage();
    }

    @Override
    public ResetPasswordPage isPageOpened() {
        $(RESET_PASSWORD_TITLE).shouldBe(visible);
        return this;
    }
}
