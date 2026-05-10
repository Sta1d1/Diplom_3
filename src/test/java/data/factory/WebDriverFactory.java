package data.factory;

import data.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver createDriver() {
        return createDriver(Settings.getBrowser());
    }

    public static WebDriver createDriver(String browserName) {
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "yandex":
                ChromeOptions options = new ChromeOptions();
                String yandexPath = String.format(Settings.getYandexBrowserPath(), System.getProperty("user.name"));
                options.setBinary(yandexPath);
                options.setBrowserVersion("146");
                driver = new ChromeDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName
                        + ". Допустимые значения: chrome, yandex");
        }

        driver.manage().window().maximize();
        driver.get(Settings.getBaseUrl());
        return driver;
    }
}
