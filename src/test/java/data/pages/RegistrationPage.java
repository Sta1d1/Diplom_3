package data.pages;

import data.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPage {
    private WebDriver driver;


    public static String baseRegistrationUrl = Settings.getBaseUrl() + "/register";

    // Элементы на вкладке регистрации
    private final By buttonLogin = By.xpath("//a[text()='Войти']");
    private final By inputRegistrationName = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By inputRegistrationEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By inputRegistrationPassword = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By errorIncorrectPasswordText = By.xpath("//p[@class='input__error text_type_main-default']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перехожу на страницу Регистрации")
    public void getRegistrationUrl() {
        driver.get(baseRegistrationUrl);
        checkRegistrationUrl();
    }

    @Step("Нажимаю кнопку <Войти> на странице регистрации")
    public void clickLoginButton(){
        driver.findElement(buttonLogin).click();
    }

    @Step("Проверяю что нахожусь на странице регистрации")
    private void checkRegistrationUrl() {
        assertEquals(driver.getCurrentUrl(), baseRegistrationUrl);
    }

    @Step("Ввожу имя")
    private void setName(String name) {
        driver.findElement(inputRegistrationName).sendKeys(name);
    }

    @Step("Ввожу email")
    private void setEmail(String email) {
        driver.findElement(inputRegistrationEmail).sendKeys(email);
    }

    @Step("Ввожу пароль")
    private void setPassword(String password) {
        driver.findElement(inputRegistrationPassword).sendKeys(password);
    }

    @Step("Нажимаю на кнопку регистрации")
    private void clickOnTheRegistrationButton(){
        driver.findElement(registrationButton).click();
    }

    @Step("Начинаю выполнение регистрации")
    public void registration (String name, String email, String password){
        getRegistrationUrl();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickOnTheRegistrationButton();
    }

    @Step("Начинаю выполнение регистрации с некорректным паролем")
    public void registrationWithIncorrectLenghtPassword(String name, String email){
        registration(name, email, "123");
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> d.findElement(errorIncorrectPasswordText).isDisplayed());
        String errorText = driver.findElement(errorIncorrectPasswordText).getText();
        assertEquals("Некорректный пароль", errorText);
    }

}
