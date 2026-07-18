package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ChangeProfileSettingsTest extends BaseTest {

    @Test(testName = "Успешное изменение изображения профиля")
    @Owner("Rudinskaya Y.V.")
    @Feature("Profile")
    @Severity(SeverityLevel.NORMAL)
    public void changeValidProfilePicture() {
        loginStep.loginWithCredentials(user, password);  //применить проперти ридер
        profilePage.openPage()
                .changeProfilePicture("avatar.png")
                .getNotificationForSuccessfulAvatarUpload()
                .shouldBe(visible)
                .shouldHave(text("Avatar was successfully updated."));
    }
}
