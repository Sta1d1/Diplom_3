package tests;

import data.factory.WebDriverFactory;
import data.pages.ConstructorPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class ConstructorTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Булочки")
    public void checkingTransitionToTheBunsSection() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.getConstructorUrl();
        constructorPage.clickTransitionToBuns();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    public void checkingTransitionToTheSauseSection() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.getConstructorUrl();
        constructorPage.clickTransitionToSauces();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    public void checkingTransitionToTheToppingsSection() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.getConstructorUrl();
        constructorPage.clickTransitionToToppings();
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
