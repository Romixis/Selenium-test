import java.time.Duration;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HabrTest {

    @Test
    public void firstTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-test-main\\drivers\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 1025));

        System.out.println("test");
        driver.get("https://habr.com/ru/all/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("timeout");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='tm-svg-img tm-header-user-menu__icon tm-header-user-menu__icon_search tm-header-user-menu__icon_dark']")));
        searchIcon.click();

        WebElement input = driver.findElement(By.xpath("//input[@name='q']"));
        Assert.assertEquals(input, driver.switchTo().activeElement());
        String searchQuery = "Selenium WebDriver";
        input.sendKeys(searchQuery);

        WebElement magnifyingGlass = driver.findElement(By.xpath("//*[@class='tm-svg-img tm-svg-icon']"));
        magnifyingGlass.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.linkText("Что такое Selenium?")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Assert.assertEquals("28 сен 2012 в 16:14", driver.findElement(By.cssSelector("[title='2012-09-28, 16:14']")).getText());

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement footerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/ru/articles/' and @class='footer-menu__item-link router-link-active']")));
        footerLink.click();

        driver.quit();
    }
}
