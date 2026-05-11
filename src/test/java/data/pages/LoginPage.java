package data.pages;

import data.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage {
    private WebDriver driver;

    public static String baseLoginUrl = Settings.getBaseUrl() + "/login";

    // Элементы на вкладке "Логин"
    private By inputEmail = By.xpath("//input[@name='name']");
    private By inputPassword = By.xpath("//input[@name='Пароль']");
    private By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перехожу на страницу Логина")
    public void getLoginUrl(){
        driver.get(baseLoginUrl);
        checkLoginUrl();
    }

    @Step("Проверяю что нахожусь на странице логина")
    public void checkLoginUrl() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(baseLoginUrl));
        assertEquals(baseLoginUrl, driver.getCurrentUrl());
    }

    @Step("Ввожу email")
    private void setEmail (String email){
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("Ввожу пароль")
    private void setPassword (String password){
        driver.findElement(inputPassword).sendKeys(password);
    }

    @Step("Нажимаю на кнопку Авторизации")
    private void clickOnTheLoginButton(){
        driver.findElement(loginButton).click();
    }

    @Step("Начинаю выполнение авторизации")
    public void login(String email, String password){
        setEmail(email);
        setPassword(password);
        clickOnTheLoginButton();
    }




}
