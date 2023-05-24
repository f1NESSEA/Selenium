package ru.netology;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private  WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
       // ChromeOptions options = new ChromeOptions();
       // options.addArguments("--disable-dev-shm-usage");
       // options.addArguments("--no-sandbox");
       // options.addArguments("--headless");
       // driver = new ChromeDriver(options);
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
       // driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestSomething() {
        // throw new UnsupportedOperationException();
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Джек Дэниэлс");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79157898797");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[type=button]")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals(expected, actual);

    }

}
