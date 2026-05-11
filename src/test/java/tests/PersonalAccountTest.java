package tests;

import data.factory.WebDriverFactory;
import data.pages.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class PersonalAccountTest {
    private WebDriver driver;
    RegistrationApi registrationApi;

    String name;
    String email;
    String password;
    String token;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();

        registrationApi = new RegistrationApi();
        name = "User" + System.currentTimeMillis();
        email = "user" + System.currentTimeMillis() + "@yandex.ru";
        password = "pass123456";
        Response registerResponse = registrationApi.registerUser(name, email, password);
        token = registerResponse.path("accessToken");

        // Логин через UI, чтобы браузер был авторизован
        ConstructorPage constructorPage = new ConstructorPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        constructorPage.clickOnTheLogInYourAccount();
        loginPage.login(email, password);
    }

    @Test
    @DisplayName("Проверка перехода в Личный кабинет через хедер")
    public void checkTransferToYourPersonalAccount() {
        Header header = new Header(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountPage();
    }

    @Test
    @DisplayName("Проверка перехода на вкладку конструктор через хедер из профиля пользовтеля")
    public void checkTransferToConstructorClickConstructorInHeader() {
        Header header = new Header(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountPage();
        header.clickOnTheConstructorInHeader();
        constructorPage.checkConstructorUrl();
    }

    @Test
    @DisplayName("Проверка перехода на вкладку конструктор через логотип в хедере")
    public void checkTransferToConstructorClickLogoInHeader() {
        Header header = new Header(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountPage();
        header.clickOnTheLogoInHeader();
        constructorPage.checkConstructorUrl();
    }

    @Test
    @DisplayName("Проверка logout пользователя")
    public void checkLogoutUser() {
        Header header = new Header(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountPage();
        personalAccountPage.logout();
        loginPage.checkLoginUrl();
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
