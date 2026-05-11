package data.pages;

import data.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorPage {
    private WebDriver driver;

    public static String baseConstructorUrl = Settings.getBaseUrl() + "/";

    //Элементы на вкладке Конструктора булок
    private final By buttonLogInToYourAccout = By.xpath("//button[text()='Войти в аккаунт']");
    private final By buttonBunsInSection = By.xpath("//span[text()='Булки']");
    private final By buttonSaucesInSection = By.xpath("//span[text()='Соусы']");
    private final By buttonToppingsInSection = By.xpath("//span[text()='Начинки']");

    // Таб-родитель с классом, содержащим "current" при активном разделе
    private final By tabBuns = By.xpath("//span[text()='Булки']/parent::div");
    private final By tabSauces = By.xpath("//span[text()='Соусы']/parent::div");
    private final By tabToppings = By.xpath("//span[text()='Начинки']/parent::div");

    private static final String ACTIVE_TAB_CLASS = "tab_tab_type_current";

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перехожу на страницу Конструктора бургеров")
    public void getConstructorUrl() {
        driver.get(baseConstructorUrl);
        checkConstructorUrl();
    }

    @Step("Проверяю что нахожусь на странице Конструктора бургеров")
    public void checkConstructorUrl() {
        assertEquals(driver.getCurrentUrl(), baseConstructorUrl);
    }

    @Step("Нажимаю на кнопку <Войти в аккаунт> на странице конструктора бургеров")
    public void clickOnTheLogInYourAccount() {
        getConstructorUrl();
        driver.findElement(buttonLogInToYourAccout).click();
    }

    @Step("Нажимаю на переход к разделу Булки")
    public void clickTransitionToBuns() {
        clickWithJs(buttonBunsInSection);
        checkTransitionToBuns();
    }

    @Step("Проверяю переход к разделу Булки")
    public void checkTransitionToBuns() {
        assertTrue(isTabActive(tabBuns), "Таб 'Булки' не активен");
    }

    @Step("Нажимаю на переход к разделу Соусы")
    public void clickTransitionToSauces() {
        clickWithJs(buttonSaucesInSection);
        checkTransitionToSauces();
    }

    @Step("Проверяю переход к разделу Соусы")
    public void checkTransitionToSauces() {
        assertTrue(isTabActive(tabSauces), "Таб 'Соусы' не активен");
    }

    @Step("Нажимаю на переход к разделу Начинки")
    public void clickTransitionToToppings() {
        clickWithJs(buttonToppingsInSection);
        checkTransitionToToppings();
    }

    @Step("Проверяю переход к разделу Начинки")
    public void checkTransitionToToppings() {
        assertTrue(isTabActive(tabToppings), "Таб 'Начинки' не активен");
    }

    private boolean isTabActive(By tabLocator) {
        WebElement tab = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(tabLocator));
        return tab.getAttribute("class").contains(ACTIVE_TAB_CLASS);
    }

    private void clickWithJs(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

}
