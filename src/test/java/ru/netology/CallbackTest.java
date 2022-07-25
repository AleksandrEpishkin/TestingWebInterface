package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:7777/");
    }

    @Test
    void PositiveTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79991112233");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();

        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее" +
                " время.", text.trim());

    }

    @Test
    void Positive2NameTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов-Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79991112233");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();

        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее" +
                " время.", text.trim());

    }

    @Test
    void NegativeNameTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ivaniov Ivan");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79991112233");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы" +
                " и дефисы.", text.trim());

    }

    @Test
    void NegativeNameTest2() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("123455");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79991112233");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы" +
                " и дефисы.", text.trim());

    }

    @Test
    void NegativeNameTest3() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("!№;%:?*");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79991112233");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы" +
                " и дефисы.", text.trim());

    }

    @Test
    void NegativePhoneTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+799911122");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                text.trim());

    }

    @Test
    void NegativePhoneTest2() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+799911122333");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                text.trim());
    }

    @Test
    void NegativePhoneTest3() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("!№;%:?*");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                text.trim());
    }

    @Test
    void EmptyPoleNameTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+799911122333");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Поле обязательно для заполнения",
                text.trim());
    }

    @Test
    void EmptyPolePhoneTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Поле обязательно для заполнения",
                text.trim());
    }

    @Test
    void CheckBoxNegativeTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван Сергеевич");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+75551112223");
        driver.findElement(By.cssSelector("[type = 'button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid")).getText();

        Assertions.assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных" +
                " и разрешаю сделать запрос в бюро кредитных историй", text.trim());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


}
