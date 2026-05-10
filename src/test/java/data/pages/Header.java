package data.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private WebDriver driver;
    private LoginPage loginPage;

    // Элементы в хедере
    private By personalAccount = By.xpath("//p[text()='Личный Кабинет']");
    private By constructor = By.xpath("//p[text()='Конструктор']");
    private By logo = By.cssSelector(".AppHeader_header__logo__2D0X2");

    public Header(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    @Step("Нажимаю на Личный кабинет в хедере")
    public void clickOnThePersonalAccount() {
        driver.findElement(personalAccount).click();
    }

    @Step("Нажимаю на вкладку конструктор в хедере")
    public void clickOnTheConstructorInHeader() {
        driver.findElement(constructor).click();
    }

    @Step("Нажимаю на логотип в хедере")
    public void clickOnTheLogoInHeader() {
        driver.findElement(logo).click();
    }

}
