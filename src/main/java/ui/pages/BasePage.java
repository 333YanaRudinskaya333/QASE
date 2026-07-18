package ui.pages;

public abstract class BasePage {

    public final String LOGIN_PAGE = "/login";
    public final String PROFILE_SETTING_PAGE = "/user/profile";
    public final String PROJECTS_PAGE = "/projects";
    public final String RESET_PASSWORD_PAGE = "/password/reset";
    public final String TEST_PLANS_PAGE = "/plan/";
    public final String FILE_AVATAR_PATH = "src/test/resources/";

    public abstract BasePage isPageOpened();
}
