package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static dict.Elements.*;

public class LoginPage extends BasePage {
    private static final String LOGIN = "[name=email]";
    private static final String PASSWORD = "[name=password]";

    public LoginPage openPage() {
        open("/login");
        return this;
    }

    public ProjectsPage login(String user, String password) {
        $(shadowCss("#accept", "#usercentrics-cmp-ui")).click();
        $(LOGIN).val(user);
        $(PASSWORD).val(password);
        $(byText(SIGN_IN_BUTTON)).click();
        return new ProjectsPage();
    }

    @Override
    public LoginPage isPageOpened() {
        $(LOGIN_TITLE).shouldBe(Condition.visible);
        return this;
    }
}
