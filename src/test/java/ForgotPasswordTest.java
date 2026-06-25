import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ForgotPasswordTest extends BaseTest {

    @Test
    public void resetPasswordWithValidEmail() {
        resetPasswordPage.openPage()
                .resetPassword("123rudinskaya.yana@gmail.com");
        resetPasswordPage.getNotificationForSuccessfulReset()
                .shouldBe(visible)
                .shouldHave(text("We have e-mailed your password reset link!"));
    }

    @Test
    public void resetPasswordWithInvalidEmailValue() {
        String invalidEmail = "11111";
        resetPasswordPage.openPage()
                .resetPasswordWithInvalidEmail(invalidEmail);
        resetPasswordPage.getErrorNotification()
                .shouldBe(visible)
                .shouldHave(text("Value '" + invalidEmail + "' does not match format email of type string"));
    }

    @Test
    public void resetPasswordWithEmptyEmail() {
        resetPasswordPage.openPage()
                .resetPassword(""); //применить проперти ридер
        resetPasswordPage.getErrorForEmptyEmail()
                .shouldBe(visible)
                .shouldHave(text("This field is required"));
    }

    @Test
    public void goToTheLogInPage() {
        resetPasswordPage
                .openPage()
                .clickLogIn();
        loginPage.isPageOpened();
    }
}
