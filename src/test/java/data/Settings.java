package data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final String DEFAULT_BASE_URL = "https://qa-stellarburgers.education-services.ru";
    private static final String DEFAULT_YANDEX_BROWSER_PATH =
            "C:\\Users\\%s\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";

    private static final Properties properties = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = Settings.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            System.err.println("Не удалось загрузить " + PROPERTIES_FILE + ": " + e.getMessage());
        }
        return props;
    }

    /**
     * Приоритет: системная переменная (-Dbrowser=...) → переменная окружения (BROWSER) → config.properties → дефолт.
     */
    public static String getBrowser() {
        return resolve("browser", "BROWSER", DEFAULT_BROWSER);
    }

    public static String getBaseUrl() {
        return resolve("base.url", "BASE_URL", DEFAULT_BASE_URL);
    }

    public static String getYandexBrowserPath() {
        return resolve("yandex.browser.path", "YANDEX_BROWSER_PATH", DEFAULT_YANDEX_BROWSER_PATH);
    }

    private static String resolve(String propertyKey, String envKey, String defaultValue) {
        String value = System.getProperty(propertyKey);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        value = System.getenv(envKey);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        value = properties.getProperty(propertyKey);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return defaultValue;
    }
}
