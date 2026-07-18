package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ForgotPasswordTest extends BaseTest {

    @Test(testName = "Восстановление пароля с вводом валидного email")
    @Owner("Rudinskaya Y.V.")
    @Feature("Forgot password")
    @Severity(SeverityLevel.CRITICAL)
    public void resetPasswordWithValidEmail() {
        resetPasswordPage.openPage()
                .resetPassword(user);
        resetPasswordPage.getNotificationForSuccessfulReset()
                .shouldBe(visible)
                .shouldHave(text("We have e-mailed your password reset link!"));
    }

    @Test(testName = "Восстановление пароля с вводом НЕ валидного email")
    @Owner("Rudinskaya Y.V.")
    @Feature("Forgot password")
    @Severity(SeverityLevel.MINOR)
    public void resetPasswordWithInvalidEmailValue() {
        String invalidEmail = "11111";
        resetPasswordPage.openPage()
                .resetPasswordWithInvalidEmail(invalidEmail);
        resetPasswordPage.getErrorNotification()
                .shouldBe(visible)
                .shouldHave(text("Value '" + invalidEmail + "' does not match format email of type string"));
    }

    @Test(testName = "Восстановление пароля с вводом пустого email")
    @Owner("Rudinskaya Y.V.")
    @Feature("Forgot password")
    @Severity(SeverityLevel.MINOR)
    public void resetPasswordWithEmptyEmail() {
        resetPasswordPage.openPage()
                .resetPassword("");
        resetPasswordPage.getErrorForEmptyEmail()
                .shouldBe(visible)
                .shouldHave(text("This field is required"));
    }

    @Test(testName = "Переход на страницу логина со страницы восстановления пароля")
    @Owner("Rudinskaya Y.V.")
    @Feature("Forgot password")
    @Severity(SeverityLevel.MINOR)
    public void goToTheLogInPage() {
        resetPasswordPage
                .openPage()
                .clickLogIn();
        loginPage.isPageOpened();
    }
}
