package Teste;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FrameTests {

    public WebDriver driver;


    @Test
    public void TestAutomat(){
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("http://demo.automationtesting.in/Register.html");
        driver.manage().window().maximize();
        //wait implicit
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS );


        String ExpectedRegisterPageTitle = "Register";
        String ActualRegisterPageTitle = driver.getTitle();
        Assert.assertEquals("Pagina Register nu are un titlu corect", ExpectedRegisterPageTitle, ActualRegisterPageTitle);

       //daca avem un text salvat in interiorul HTML scris cu negru la capatul liniei >textul<
        //Putem folosi contains cand identificam dupa XPath
        ////a[contains(text(),'Switch')]


        WebElement SwitchtomeniuWeb=driver.findElement(By.xpath("//a[contains(text(),'Switch')]"));
        Actions Action=new Actions(driver);
        Action.moveToElement(SwitchtomeniuWeb).build().perform();
        WebElement FramesSubMeniuWeb=driver.findElement(By.xpath("//a[contains(text(),'Frames')]"));


        //declaram un wait explicit care sa astepte dupa element
        new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOf(FramesSubMeniuWeb));
        FramesSubMeniuWeb.click();
        //inchidem pop-ul de reclame
        driver.switchTo().frame("aswift_2");
        List<WebElement>checkPopUpDisplayedList=driver.findElements(By.xpath("//iframe[@id='ad_iframe']"));
        if(checkPopUpDisplayedList.size()>0){
            new WebDriverWait(driver,15).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ad_iframe"));
            WebElement ClosepopupWeb=driver.findElement(By.xpath("//span[contains(text(),'Close')]"));
            ClosepopupWeb.click();
        }


        //Ne putem muta pe un Iframe dupa id,nume sau webelement
        List <WebElement> FrameButtons=driver.findElements(By.xpath("//ul[@class='nav nav-tabs ']/li/a"));
        FrameButtons.get(0).click();

        driver.switchTo().frame("singleframe");
        WebElement InputSingleFrame=driver.findElement(By.xpath("//input[@type='text']"));
        InputSingleFrame.sendKeys("Vreau concediu");
        //dupa ce am terminat de lucrat cu un Iframe, trebuie sa ne mutam pe frame ul default
        driver.switchTo().defaultContent();

        FrameButtons.get(1).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//Iframe[@src='MultipleFrames.html']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//Iframe[@src='SingleFrame.html']")));
        WebElement InputMultipleFrame=driver.findElement(By.xpath("//input[@type='text']"));
        InputMultipleFrame.sendKeys("Cel mai tare din parcare");
        driver.switchTo().defaultContent();
        driver.quit();





    }


}
