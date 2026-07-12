package pages;
import org.openqa.selenium.By;
import wrappers.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.*;

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
    private final Dropdown tagsDropdown = new Dropdown("Tags");
    private final RichTextEditor descriptionField = new RichTextEditor("Description");
    private final RichTextEditor preConditionsField = new RichTextEditor("Pre-conditions");
    private final RichTextEditor postConditionsField = new RichTextEditor("Post-conditions");
    private final Checkbox isMutedCheckbox = new Checkbox("Muted case");


    public CreateTestCasePage fillTestCaseForm(String title, String status, String suite, String severity,
                                               String priority, String type, String layer, String isFlaky, String milestone,
                                               String behavior, String automation, String description,
                                               String preConditions, String postConditions, boolean isMuted) {

        titleInput.write(title);
        statusDropdown.selectOption(status);
        suiteDropdown.selectOption(suite);
        severityDropdown.selectOption(severity);
        priorityDropdown.selectOption(priority);
        typeDropdown.selectOption(type);
        layerDropdown.selectOption(layer);
        isFlakyDropdown.selectOption(isFlaky);
        milestoneDropdown.selectOption(milestone);
        behaviorDropdown.selectOption(behavior);
        automationDropdown.selectOption(automation);
        descriptionField.write(description);
        preConditionsField.write(preConditions);
        postConditionsField.write(postConditions);
        isMutedCheckbox.setStatus(isMuted);
        return this;
    }

    public CreateTestCasePage addParameter(int index, String name, String value) {
        $(byText(NEW_PARAMETER_BUTTON_ON_CREATE_CASE_PAGE)).click();
        new TestCaseParameter(index).fill(name, value);
        return this;
    }


    public ProjectPage clickSaveButton() {
        $(byText(SAVE_BUTTON_ON_CREATE_CASE_PAGE)).click();
        return new ProjectPage();
    }


    @Override
    public CreateTestCasePage isPageOpened() {
        $(CREATE_TEST_CASE_TITLE).shouldBe(visible);
        return this;
    }
}
