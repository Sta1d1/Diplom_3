package tests;

import data.factory.WebDriverFactory;
import data.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class PersonalAccountTest {
    private WebDriver driver;

    String name;
    String email;
    String password;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        name = "User" + System.currentTimeMillis();
        email = "user" + System.currentTimeMillis() + "@yandex.ru";
        password = "pass123456";
        registrationPage.registration(name, email, password);
        loginPage.getLoginUrl();
        loginPage.login(email, password);
    }

    @Test
    @DisplayName("Проверка перехода в Личный кабинет через хедер")
    public void checkTransferToYourPersonalAccount() {
        Header header = new Header(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountUrl();
    }

    @Test
    @DisplayName("Проверка перехода на вкладку конструктор через хедер из профиля пользовтеля")
    public void checkTransferToConstructorClickConstructorInHeader() {
        Header header = new Header(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);

        header.clickOnThePersonalAccount();
        personalAccountPage.checkPersonalAccountUrl();
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
        personalAccountPage.checkPersonalAccountUrl();
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
        personalAccountPage.checkPersonalAccountUrl();
        personalAccountPage.logout();
        loginPage.checkLoginUrl();
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
