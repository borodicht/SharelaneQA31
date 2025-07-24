import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ZipCodeTest {

    /*
    1. Открыть браузер chrome
    2. В браузере открыть страницу https://www.sharelane.com/cgi-bin/register.py
    3. Ввести в поле Zip Code 4 цифр, например 1234
    4. Нажать кнопку Continue
    5. Проверить, что на экране появилась ошибка с тестом Oops, error on page. ZIP code should have 5 digits
    6. Закрыть браузер
     */

    @Test
    public void test() {
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("1234");
        browser.findElement(By.cssSelector("[value=Continue]")).click();
        String errorMessage = browser.findElement(By.className("error_message")).getText();
        Assert.assertEquals(errorMessage, "Oops, error on page. ZIP code should have 5 digits");
        browser.quit();
    }

    @Test
    public void test1() {
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        browser.findElement(By.cssSelector("[value=Continue]")).click();


        browser.quit();
    }

    @Test
    public void testEnterValidValueIntoTheFieldZipCode() {
        WebDriver driver = new ChromeDriver();
        String zipcode = "1111";
        String expectedUrl = "https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=" + zipcode;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys(zipcode);
        driver.findElement(By.cssSelector("[value=Continue]")).click();

        WebElement firstName = driver.findElement(By.name("first_name"));
        WebElement lastName = driver.findElement(By.name("last_name"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password1 = driver.findElement(By.name("password1"));
        WebElement password2 = driver.findElement(By.name("password2"));

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(firstName.isDisplayed());
        Assert.assertTrue(lastName.isDisplayed());
        Assert.assertTrue(email.isDisplayed());
        Assert.assertTrue(password1.isDisplayed());
        Assert.assertTrue(password2.isDisplayed());
        Assert.assertTrue(expectedUrl.contains(currentUrl));

        driver.quit();
    }

}
