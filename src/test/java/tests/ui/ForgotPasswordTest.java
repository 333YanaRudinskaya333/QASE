package tests.ui;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ForgotPasswordTest extends BaseTest {

    @Test(testName = "Восстановление пароля с вводом валидного email")
    public void resetPasswordWithValidEmail() {
        resetPasswordPage.openPage()
                .resetPassword("123rudinskaya.yana@gmail.com"); //применить проперти ридер
        resetPasswordPage.getNotificationForSuccessfulReset()
                .shouldBe(visible)
                .shouldHave(text("We have e-mailed your password reset link!"));
    }

    @Test(testName = "Восстановление пароля с вводом НЕ валидного email")
    public void resetPasswordWithInvalidEmailValue() {
        String invalidEmail = "11111";
        resetPasswordPage.openPage()
                .resetPasswordWithInvalidEmail(invalidEmail);
        resetPasswordPage.getErrorNotification()
                .shouldBe(visible)
                .shouldHave(text("Value '" + invalidEmail + "' does not match format email of type string"));
    }

    @Test(testName = "Восстановление пароля с вводом пустого email")
    public void resetPasswordWithEmptyEmail() {
        resetPasswordPage.openPage()
                .resetPassword("");
        resetPasswordPage.getErrorForEmptyEmail()
                .shouldBe(visible)
                .shouldHave(text("This field is required"));
    }

    @Test(testName = "Переход на страницу логина со страницы восстановления пароля")
    public void goToTheLogInPage() {
        resetPasswordPage
                .openPage()
                .clickLogIn();
        loginPage.isPageOpened();
    }
}
