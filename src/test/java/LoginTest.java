import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class LoginTest extends BaseTest {

    @Test
    public void loginWithPositiveCred() {
        loginPage.openPage()
                .login("rudinskaya.yana@gmail.com", "TeSt123Qq===");  //применить проперти ридер
        projectsPage.getProjectTitle()
                .shouldBe(visible)
                .shouldHave(text("Projects"));
    }

    @DataProvider(name = "Параметризованный тест для негативного логина с заполненными некорректными именем пользователя и(или) паролем")
    public Object[][] loginData() {
        return new Object[][]{
                {"11123131312xxxq@gmail.ru", "TeSt123Qq===", "Поле имя пользователя должно быть невалидным"}, //применить проперти ридер
                {"rudinskaya.yana@gmail.com", "TeSt123Qq===111", "Пароль для входа должен быть невалидный"}, //применить проперти ридер
                {"11123131312xxxq@gmail.ru", "TeSt123Qq===111", "Поля имя пользователя и пароль должны быть невалидными"}
        };
    }

    @Test(dataProvider = "Параметризованный тест для негативного логина с заполненными некорректными именем пользователя и(или) паролем")
    public void loginWithNegativeCred(String user, String password, String errorMessage) {
        loginPage.openPage()
                .login(user, password);
        loginPage.getErrorMessage()
                .shouldBe(visible)
                .shouldHave(text("These credentials do not match our records."));
    }

    @Test
    public void loginWithEmptyCred() {
        loginPage.openPage()
                .login("", ""); //применить проперти ридер
        loginPage.getErrorsForEmptyUserNameAndPassword()
                .shouldHave(texts("This field is required", "This field is required"));
    }

    @Test
    public void logOutTest() {
        loginPage.openPage()
                .login("rudinskaya.yana@gmail.com", "TeSt123Qq===")//применить проперти ридер
                .isPageOpened()
                .signOut()
                .isPageOpened();
    }
}
