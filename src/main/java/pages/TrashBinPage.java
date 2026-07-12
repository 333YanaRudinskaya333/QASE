package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static dict.Elements.*;

public class TrashBinPage extends BasePage{
    public static final By TRASH_BIN_TITLE = By.cssSelector("h1");
    public static final String NOTIFICATION_FOR_SUCCESSFUL_DELETE_TEST_CASE ="//span[contains(text(), 'Items restored successfully')]";
    public static final By selectAllTrashCheckbox = By.cssSelector("thead th label[data-react-aria-pressable='true'], thead th input[type='checkbox']");

    public TrashBinPage restoreAllTestCasesFromTrashBin() {
        $(selectAllTrashCheckbox).click();
        $(byText(RESTORE_SELECTED_BUTTON)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessRestore() {
        return $x(NOTIFICATION_FOR_SUCCESSFUL_DELETE_TEST_CASE);
    }

    @Override
    public TrashBinPage isPageOpened() {
        $(TRASH_BIN_TITLE).shouldBe(Condition.visible);
        return this;
    }
}
