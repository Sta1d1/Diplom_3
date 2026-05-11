package tests;

import data.factory.WebDriverFactory;
import data.pages.LoginPage;
import data.pages.RegistrationApi;
import data.pages.RegistrationPage;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class RegistrationTest {
    private WebDriver driver;
    RegistrationApi registrationApi;
    private String token;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        registrationApi = new RegistrationApi();
    }

    @Test
    @DisplayName("Проверка корректной регистрации")
    public void checkCorrectRegistration() {
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        String name = "User" + System.currentTimeMillis();
        String email = "user" + System.currentTimeMillis() + "@yandex.ru";
        String password = "pass123456";

        registrationPage.registration(name, email, password);
        loginPage.getLoginUrl();
        loginPage.login(email, password);

        Response registerResponse = registrationApi.loginUser(email, password);
        token = registerResponse.path("accessToken");
    }

    @Test
    @DisplayName("Проверка что нельзя зарегистрироваться если пользователь ввел пароль меньше 6 символов")
    public void checkIncorrectRegistrationWithPassLess6Symbols() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        String name = "User" + System.currentTimeMillis();
        String email = "user" + System.currentTimeMillis() + "@yandex.ru";

        registrationPage.registrationWithIncorrectLenghtPassword(name, email);

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
