package ui.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.pages.ProjectPage;
import ui.pages.LoginPage;
import ui.pages.ProjectsPage;

@Log4j2
public class ProjectStep {

    private final ProjectsPage projectsPage = new ProjectsPage();
    private final ProjectPage projectPage = new ProjectPage();

    @Step("Создание нового проекта с именем: {name} и кодом: {code}")
    public ProjectPage createNewProject(String name, String code) {
        log.info("Создание нового проекта через UI. Имя: '{}', Код: '{}'", name, code);
        projectsPage.createProject();
        return projectPage.isPageOpened();
    }

    @Step("Предусловие: Авторизация и создание тестового проекта '{name}'")
    public ProjectPage loginAndCreateTestProject(String user, String password, String name, String code) {
        log.info("Старт предусловия: авторизация пользователя [{}] и генерация проекта '{}'", user, name);
        new LoginPage()
                .openPage()
                .isPageOpened()
                .login(user, password);
        this.createNewProject(name, code);
        ProjectPage projectPage = new ProjectPage();
        projectPage.getProjectTitleElement().shouldHave(Condition.text(name));
        return projectPage;
    }

    @Step("Предусловие: Авторизация, создание проекта '{name}' и возврат на главную страницу")
    public ProjectsPage loginCreateProjectAndReturnToProjectsPage(String user, String password, String name, String code) {
        log.info("Старт предусловия: создание проекта с последующим переходом к дашборду проектов");
        loginAndCreateTestProject(user, password, name, code);
        return new ProjectsPage().openPage();
    }

    @Step("Удаление проекта с именем: {name}")
    public ProjectsPage deleteProjectIfExists(String name) {
        log.info("Запуск очистки данных: удаление проекта с именем '{}', если он существует", name);
        return projectsPage.openPage()
                .deleteProject(name);
    }
}
