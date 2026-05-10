package data.pages;

import data.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalAccountPage {
    private WebDriver driver;

    public static String basePersonalAccountUrl = Settings.getBaseUrl() + "/account/profile";

    // Элементы на вкладке "Личный кабинет"
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываю страницу Профиля")
    public void getPersonalAccount() {
        driver.get(basePersonalAccountUrl);
    }

    @Step("Проверяю что нахожусь на странице <Личный кабинет>")
    public void checkPersonalAccountUrl() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(basePersonalAccountUrl));
        assertEquals(basePersonalAccountUrl, driver.getCurrentUrl());
    }

    @Step("Нажимаю на кнопку выйти в профиле")
    public void logout() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }


}
