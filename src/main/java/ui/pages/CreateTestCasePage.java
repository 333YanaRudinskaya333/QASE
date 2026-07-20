package ui.pages;
import ui.dto.TestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import ui.wrappers.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class CreateTestCasePage extends BasePage {

    private static final By CREATE_TEST_CASE_TITLE = By.xpath("//h1[contains(text(),'Create test case')]");

    private final Input titleInput = new Input("Title");
    private final Dropdown statusDropdown = new Dropdown("Status");
    private final Dropdown suiteDropdown = new Dropdown("Suite");
    private final Dropdown severityDropdown = new Dropdown("Severity");
    private final Dropdown priorityDropdown = new Dropdown("Priority");
    private final Dropdown typeDropdown = new Dropdown("Type");
    private final Dropdown layerDropdown = new Dropdown("Layer");
    private final Dropdown isFlakyDropdown = new Dropdown("Is flaky");
    private final Dropdown milestoneDropdown = new Dropdown("Milestone");
    private final Dropdown behaviorDropdown = new Dropdown("Behavior");
    private final Dropdown automationDropdown = new Dropdown("Automation status");
    private final RichTextEditor descriptionField = new RichTextEditor("Description");
    private final RichTextEditor preConditionsField = new RichTextEditor("Pre-conditions");
    private final RichTextEditor postConditionsField = new RichTextEditor("Post-conditions");
    private final Checkbox isMutedCheckbox = new Checkbox("Muted case");

    @Step("Заполнить форму создания тест-кейса: '{testCase.title}'")
    public CreateTestCasePage fillTestCaseForm(TestCase testCase) {
        log.info("Filling test case form for case: {}", testCase.getTitle());
        if (testCase.getTitle() != null) titleInput.write(testCase.getTitle());
        if (testCase.getStatus() != null) statusDropdown.selectOption(testCase.getStatus());
        if (testCase.getSuite() != null) suiteDropdown.selectOption(testCase.getSuite());
        if (testCase.getSeverity() != null) severityDropdown.selectOption(testCase.getSeverity());
        if (testCase.getPriority() != null) priorityDropdown.selectOption(testCase.getPriority());
        if (testCase.getType() != null) typeDropdown.selectOption(testCase.getType());
        if (testCase.getLayer() != null) layerDropdown.selectOption(testCase.getLayer());
        if (testCase.getIsFlaky() != null) isFlakyDropdown.selectOption(testCase.getIsFlaky());
        if (testCase.getMilestone() != null) milestoneDropdown.selectOption(testCase.getMilestone());
        if (testCase.getBehavior() != null) behaviorDropdown.selectOption(testCase.getBehavior());
        if (testCase.getAutomation() != null) automationDropdown.selectOption(testCase.getAutomation());
        if (testCase.getDescription() != null) descriptionField.write(testCase.getDescription());
        if (testCase.getPreConditions() != null) preConditionsField.write(testCase.getPreConditions());
        if (testCase.getPostConditions() != null) postConditionsField.write(testCase.getPostConditions());
        isMutedCheckbox.setStatus(testCase.isMuted());
        log.info("Test case form has been filled successfully");
        return this;
    }

    @Step("Добавить параметр под индексом {index}. Имя: '{name}', Значение: '{value}'")
    public CreateTestCasePage addParameter(int index, String name, String value) {
        log.info(String.format("Adding parameter at index %d. Name: '%s', Value: '%s'", index, name, value));
        $(byText(NEW_PARAMETER_BUTTON_ON_CREATE_CASE_PAGE)).click();
        new TestCaseParameter(index).fill(name, value);
        return this;
    }

    @Step("Нажать кнопку 'Save' для сохранения тест-кейса")
    public ProjectPage clickSaveButton() {
        log.info("Clicking the 'Save' button to create the test case");
        $(byText(SAVE_BUTTON_ON_CREATE_CASE_PAGE)).click();
        return new ProjectPage();
    }


    @Override
    @Step("Проверить, что страница 'Create Test Case' успешно открыта")
    public CreateTestCasePage isPageOpened() {
        log.debug("Verifying if 'Create Test Case' page is opened");
        $(CREATE_TEST_CASE_TITLE).shouldBe(visible);
        log.info("'Create Test Case' page is successfully opened");
        return this;
    }
}
