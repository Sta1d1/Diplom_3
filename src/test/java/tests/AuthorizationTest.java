package tests;

import data.factory.WebDriverFactory;
import data.pages.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class AuthorizationTest {
    private WebDriver driver;
    RegistrationApi registrationApi;
    PersonalAccountPage personalAccountPage;
    Header header;

    String name;
    String email;
    String password;
    String token;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        personalAccountPage = new PersonalAccountPage(driver);
        header = new Header(driver);

        registrationApi = new RegistrationApi();
        name = "User" + System.currentTimeMillis();
        email = "user" + System.currentTimeMillis() + "@yandex.ru";
        password = "pass123456";
        Response registerResponse = registrationApi.registerUser(name, email, password);
        token = registerResponse.path("accessToken");
    }

    @Test
    @DisplayName("Проверка авторизации через главную страницу")
    public void checkAuthLoginToAccountOnTheMainPage() {
        ConstructorPage constructorPage = new ConstructorPage(driver);

        LoginPage loginPage = new LoginPage(driver);

        constructorPage.clickOnTheLogInYourAccount();
        loginPage.login(email, password);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountPage();
    }

    @Test
    @DisplayName("Проверка авторизации через Личный кабинет в хедере")
    public void checkAuthLogInHeader() {
        LoginPage loginPage = new LoginPage(driver);
        Header header = new Header(driver);

        header.clickOnThePersonalAccount();
        loginPage.login(email, password);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountPage();
    }

    @Test
    @DisplayName("Проверка авторизации со страницы регистрации")
    public void checkAuthFromRegisterForm() {
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.getRegistrationUrl();
        registrationPage.clickLoginButton();
        loginPage.checkLoginUrl();
        loginPage.login(email, password);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountPage();
    }

    @Test
    @DisplayName("Проверка авторизации со страницы забыл пароль")
    public void checkAuthFromForgotPasswordPage() {
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        forgotPasswordPage.getForgotPasswordPage();
        forgotPasswordPage.clickLoginButton();
        loginPage.login(email, password);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountPage();
    }


    @AfterEach
    public void tearDown() {
        try {
            if (token != null) {
                String cleanToken = token.replace("Bearer ", "");
                registrationApi.deleteUser(cleanToken);
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
