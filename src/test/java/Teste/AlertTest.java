package Teste;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class AlertTest {


    public WebDriver driver;

    @Test
    public void Alerts() {
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://demo.automationtesting.in/Register.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        String ExpectedRegisterPageTitle = "Register";
        String ActualRegisterPageTitle = driver.getTitle();
        Assert.assertEquals("Pagina Register nu are un titlu corect", ExpectedRegisterPageTitle, ActualRegisterPageTitle);

        WebElement SwitchtomeniuWeb = driver.findElement(By.xpath("//a[contains(text(),'Switch')]"));
        Actions Action = new Actions(driver);
        Action.moveToElement(SwitchtomeniuWeb).build().perform();

        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Switch')]")));
        WebElement AlertOptionWeb = driver.findElement((By.xpath("//a[contains(text(),'Alerts')]")));
        driver.navigate().to("http://demo.automationtesting.in/Alerts.html");

        List<WebElement>AlertsOptions=driver.findElements(By.xpath("//ul[@class='nav nav-tabs nav-stacked']/li/a"));
        AlertsOptions.get(0).click();
        WebElement ClickAlertOk=driver.findElement(By.xpath("//button[@onclick='alertbox()']"));
        ClickAlertOk.click();


        //declaram un obiect de tip alerta ca sa validam ca am rectionat cu ea
        new WebDriverWait(driver,15).until(ExpectedConditions.alertIsPresent());
        Alert AlertwithOk=driver.switchTo().alert();
        System.out.println(AlertwithOk.getText());
        AlertwithOk.accept();

        AlertsOptions.get(1).click();
        WebElement ClickAlertOkCancel=driver.findElement(By.xpath("//button[@onclick='confirmbox()']"));
        ClickAlertOkCancel.click();
        new WebDriverWait(driver,15).until(ExpectedConditions.alertIsPresent());
        Alert AlertOkCancel=driver.switchTo().alert();
        System.out.println(AlertOkCancel.getText());
        AlertOkCancel.dismiss();


        AlertsOptions.get(2).click();
        WebElement ClickAlertWithTextBox=driver.findElement(By.xpath("//button[@onclick='promptbox()']"));
        ClickAlertWithTextBox.click();
        new WebDriverWait(driver,15).until(ExpectedConditions.alertIsPresent());

        Alert AlertTextBox=driver.switchTo().alert();
        System.out.println(AlertTextBox.getText());
        AlertTextBox.sendKeys("El patron");
        AlertTextBox.accept();


    }
}