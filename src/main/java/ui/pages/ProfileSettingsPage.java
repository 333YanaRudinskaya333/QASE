package ui.pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class ProfileSettingsPage extends BasePage {
    private static final By PROFILE_TITLE = By.xpath("//h1[contains(text(),'Profile Settings')]");
    private static final By UPLOAD_BUTTON = By.cssSelector("input[type='file']");
    public static final String NOTIFICATION_FOR_SUCCESSFUL_AVATAR_UPLOAD = ("//span[contains(text(), 'Avatar was successfully updated.')]");

    @Step("Открыть страницу настроек профиля")
    public ProfileSettingsPage openPage() {
        log.info("Opening Profile Settings page");
        open(PROFILE_SETTING_PAGE);
        return this;
    }

    @Step("Изменить изображение профиля на файл: '{fileName}'")
    public ProfileSettingsPage changeProfilePicture(String fileName) {
        log.info("Uploading profile picture. File name: '{}'", fileName);
        $(UPLOAD_BUTTON).uploadFile(new File(FILE_AVATAR_PATH + fileName));
        log.info("Clicking update settings button to apply changes");
        $(byText(UPDATE_SETTINGS_BUTTON)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessfulAvatarUpload() {
        log.debug("Getting success notification element for avatar upload");
        return $x(NOTIFICATION_FOR_SUCCESSFUL_AVATAR_UPLOAD);
    }

    @Override
    @Step("Проверить, что страница настроек профиля успешно открыта")
    public ProfileSettingsPage isPageOpened() {
        log.debug("Verifying if Profile Settings page is opened");
        $(PROFILE_TITLE).shouldBe(visible);
        log.info("Profile Settings page is successfully opened");
        return this;
    }
}
