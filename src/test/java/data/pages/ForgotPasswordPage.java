package data.pages;

import data.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForgotPasswordPage {
    private WebDriver driver;

    public static String baseForgotPasswordUrl = Settings.getBaseUrl() + "/forgot-password";

    // Элементы на вкладке "Восстановление пароля"
    private By buttonLogin = By.xpath("//a[text()='Войти']");


    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перехожу на страницу Восстановления пароля")
    public void getForgotPasswordPage() {
        driver.get(baseForgotPasswordUrl);
        checkForgotPasswordUrl();
    }

    @Step("Нажимаю кнопку <Войти> на странице регистрации")
    public void clickLoginButton(){
        driver.findElement(buttonLogin).click();
    }

    @Step("Проверяю что нахожусь на странице регистрации")
    private void checkForgotPasswordUrl() {
        assertEquals(driver.getCurrentUrl(), baseForgotPasswordUrl);
    }

}
