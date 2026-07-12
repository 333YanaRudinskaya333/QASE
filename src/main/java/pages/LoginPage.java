package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static dict.Elements.*;

public class LoginPage extends BasePage {
    private static final String USER_NAME = "[name=email]";
    private static final String PASSWORD = "[name=password]";
    private static final By LOGIN_TITLE = By.xpath("//h1[text()='Log in to your account']");
    private static final String ERRORS_FOR_EMPTY_USER_NAME_AND_PASSWORD = "//small[contains(text(), 'This field is required')]";

    public LoginPage openPage() {
        open("/login");
        return this;
    }

    public ProjectsPage login(String user, String password) {
        $(shadowCss("#accept", "#usercentrics-cmp-ui")).click();
        $(USER_NAME).val(user);
        $(PASSWORD).val(password);
        $(byText(SIGN_IN_BUTTON)).click();
        return new ProjectsPage().isPageOpened();
    }

    public SelenideElement getErrorMessage() {
        return $(byText(ERROR_MESSAGE_FOR_WRONG_USER_NAME_AND_PASSWORD));
    }

    public ElementsCollection getErrorsForEmptyUserNameAndPassword() {
        return $$x(ERRORS_FOR_EMPTY_USER_NAME_AND_PASSWORD);
    }

    public LoginPage enterCredentials(String user, String password) {
        $(USER_NAME).val(user);
        $(PASSWORD).val(password);
        return this;
    }

    public LoginPage clickSignInButton() {
        $(byText(SIGN_IN_BUTTON)).click();
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        $(LOGIN_TITLE).shouldBe(visible);
        return this;
    }
}
