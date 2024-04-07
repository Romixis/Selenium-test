import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MailRuTest {

    private WebDriver driver;
    WebElement createButton;

    private void init(){
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-test-main\\drivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 1025));
    }

    private void go() {
        driver.get("https://account.mail.ru/");
    }

    private void delay(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    private WebElement find(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    private void inputEmail(String email) {
        WebElement inputField = find("//input[@name='username']");
        inputField.clear();
        inputField.sendKeys(email);
        WebElement nextButton = find("//*[@data-test-id='next-button']");
        nextButton.click();
    }

    private void inputPassword(String password) {
        WebElement inputField = find("//input[@name='password']");
        inputField.clear();
        inputField.sendKeys(password);
        WebElement nextButton = find("//button[@data-test-id='submit-button']");
        nextButton.click();
    }

    private void openProfileMenu(){
        WebElement profile = find("//*[@class='ph-avatar-img svelte-dfhuqc']");
        profile.click();
    }

    private void checkProfileName(String name){
        String nameField = find("//*[@class='ph-name svelte-1popff4']").getText();
        Assert.assertEquals(name, nameField);
    }

    private void quit(){
        WebElement quitButton = find("//*[@data-testid='whiteline-account-exit']");
        quitButton.click();
    }

    private void checkQuit() {
        createButton = find("//*[@class='v_qUjaWvpibiBToKhTyB']");
        if(!createButton.isDisplayed()) Assert.fail();
    }
    
    @Test
    public void test(){
        init();
        go();
        delay(10);
        inputEmail("kuvshinidze@mail.ru");
        delay(10);
        inputPassword("04b-Pdw-2cJ-QX8");
        delay(10);
        openProfileMenu();
        delay(10);
        checkProfileName("Владимир Кувшинидзе");
        delay(10);
        quit();
        delay(10);
        checkQuit();
    }
}
