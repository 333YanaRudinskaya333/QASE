import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static dict.Elements.*;

public class ProjectTest extends BaseTest {

    @Test
    public void checkCreateProject() {
        loginPage.openPage()
                .login("rudinskaya.yana@gmail.com", "TeSt123Qq===");
        projectsPage.createProject().isPageOpened();
        $(REPOSITORY_TITLE).shouldHave(Condition.text("111DD"));
        projectsPage.openPage();
    }
}
