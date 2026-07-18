package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ui.dict.Elements.*;

@Log4j2
public class TrashBinPage extends BasePage {
    public static final By TRASH_BIN_TITLE = By.cssSelector("h1");
    public static final String NOTIFICATION_FOR_SUCCESSFUL_RESTORE_ITEMS = "//span[contains(text(), 'Items restored successfully')]";
    public static final By SELECTALLTRASHCHECKBOX = By.cssSelector("thead th label[data-react-aria-pressable='true'], thead th input[type='checkbox']");

    @Step("Восстановить все тест-кейсы из корзины (Trash Bin)")
    public TrashBinPage restoreAllTestCasesFromTrashBin() {
        log.info("Attempting to restore all test cases from the Trash Bin");
        log.debug("Selecting all items in the Trash Bin");
        $(SELECTALLTRASHCHECKBOX).click();
        log.info("Clicking 'Restore selected' button");
        $(byText(RESTORE_SELECTED_BUTTON)).click();
        return this;
    }

    public SelenideElement getNotificationForSuccessRestore() {
        log.debug("Getting success notification element for restoring items");
        return $x(NOTIFICATION_FOR_SUCCESSFUL_RESTORE_ITEMS);
    }

    @Override
    @Step("Проверить, что страница корзины (Trash Bin) успешно открыта")
    public TrashBinPage isPageOpened() {
        log.debug("Checking if Trash Bin page is opened");
        $(TRASH_BIN_TITLE).shouldBe(Condition.visible);
        log.info("Trash Bin page is successfully opened");
        return this;
    }
}
