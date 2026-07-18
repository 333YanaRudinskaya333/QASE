package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class LoginTest extends BaseTest {

    @Test(testName = "Успешная авторизация с валидными учетными данными")
    @Owner("Rudinskaya Y.V.")
    @Feature("Login")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithPositiveCred() {
        loginStep.loginWithCredentials(user, password);
        projectsPage.getProjectTitle()
                .shouldBe(visible)
                .shouldHave(text("Projects"));
    }

    @DataProvider(name = "negativeLoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"11123131312xxxq@gmail.ru", password, "Поле имя пользователя должно быть невалидным"},
                {user, "TeSt123Qq===111", "Пароль для входа должен быть невалидный"},
                {"11123131312xxxq@gmail.ru", "invalidPassword", "Поля имя пользователя и пароль должны быть невалидными"}
        };
    }

    @Test(
            dataProvider = "negativeLoginData",
            testName = "Негативный сценарий авторизации с некорректными данными"
    )
    @Owner("Rudinskaya Y.V.")
    @Feature("Login")
    @Severity(SeverityLevel.MINOR)
    public void loginWithNegativeCred(String user, String password, String errorMessage) {
        loginStep.loginWithInvalidCredentials(user, password);
        loginPage.getErrorMessage()
                .shouldBe(visible)
                .shouldHave(text("These credentials do not match our records."));
    }

    @Test(testName = "Попытка авторизации с пустыми полями ввода")
    @Owner("Rudinskaya Y.V.")
    @Feature("Login")
    @Severity(SeverityLevel.MINOR)
    public void loginWithEmptyCred() {
        loginStep.loginWithEmptyFields();
        loginPage.getErrorsForEmptyUserNameAndPassword()
                .shouldHave(texts("This field is required", "This field is required"));
    }

    @Test(testName = "Успешный выход из системы (LogOut)")
    @Owner("Rudinskaya Y.V.")
    @Feature("Login")
    @Severity(SeverityLevel.CRITICAL)
    public void logOutTest() {
        loginStep.loginWithCredentials(user, password);
        loginStep.logout();
    }
}
