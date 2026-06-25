import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ChangeProfileSettingsTest extends BaseTest {

    @Test
    public void changeValidProfilePicture() {
        loginPage.openPage()
                .login("rudinskaya.yana@gmail.com", "TeSt123Qq===");  //применить проперти ридер
        profilePage.openPage()
                .changeProfilePicture("avatar.png")
                .getNotificationForSuccessfulAvatarUpload()
                .shouldBe(visible)
                .shouldHave(text("Avatar was successfully updated."));
    }
}
