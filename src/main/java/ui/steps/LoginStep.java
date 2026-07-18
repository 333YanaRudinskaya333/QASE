package ui.steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.pages.LoginPage;
import ui.pages.ProjectsPage;

@Log4j2
public class LoginStep {

    private final LoginPage loginPage = new LoginPage();
    private final ProjectsPage projectsPage = new ProjectsPage();

    @Step("Успешная авторизация в системе под пользователем")
    public ProjectsPage loginWithCredentials(String user, String password) {
        log.info("Выполняется успешный вход в систему для пользователя: {}", user);
        loginPage.openPage()
                .isPageOpened()
                .login(user, password);
        return projectsPage.isPageOpened();
    }

    @Step("Попытка авторизации с некорректными данными.")
    public LoginPage loginWithInvalidCredentials(String user, String password) {
        log.info("Попытка авторизации с некорректными учетными данными. Пользователь: {}", user);
        return loginPage.openPage()
                .isPageOpened()
                .enterCredentials(user, password)
                .clickSignInButton();
    }

    @Step("Попытка авторизации с пустыми полями логина и пароля")
    public LoginPage loginWithEmptyFields() {
        log.info("Попытка авторизации с пустыми полями формы ввода");
        return loginPage.openPage()
                .isPageOpened()
                .clickSignInButton();
    }

    @Step("Выход из учетной записи")
    public LoginPage logout() {
        log.info("Запрос на выход из учетной записи текущего пользователя");
        projectsPage.isPageOpened()
                .signOut()
                .isPageOpened();
        return loginPage;
    }
}
