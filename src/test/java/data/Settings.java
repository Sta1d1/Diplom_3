package data;


public class Settings {
    private static final String BROWSER = "yandex";
    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";
    private static final String YANDEX_BROWSER_PATH = "C:\\Users\\%s\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";


    public static String getBrowser() { return BROWSER; }
    public static String getBaseUrl() { return BASE_URL; }
    public static String getYandexBrowserPath() { return YANDEX_BROWSER_PATH; }

}
