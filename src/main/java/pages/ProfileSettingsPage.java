package pages;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static dict.Elements.*;

public class ProfileSettingsPage extends BasePage {
    private static final By PROFILE_TITLE = By.xpath("//h1[contains(text(),'Profile Settings')]");
    private static final By UPLOAD_BUTTON = By.cssSelector("input[type='file']");
    public static final String NOTIFICATION_FOR_SUCCESSFUL_AVATAR_UPLOAD = ("//span[contains(text(), 'Avatar was successfully updated.')]");

    public ProfileSettingsPage openPage() {
        open("/user/profile");
        return this;
    }

    public ProfileSettingsPage changeProfilePicture(String fileName) {
        $(UPLOAD_BUTTON).uploadFile(new File("src/test/resources/" + fileName));
        $(byText(UPDATE_SETTINGS_BUTTON)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessfulAvatarUpload() {
        return $x(NOTIFICATION_FOR_SUCCESSFUL_AVATAR_UPLOAD);
    }

    @Override
    public ProfileSettingsPage isPageOpened() {
        $(PROFILE_TITLE).shouldBe(visible);
        return this;
    }
}
