package Teste;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

public class RegisterTest {


    //declaram o variabila WebDriver
    public WebDriver driver;

    @Test
    public void Register() {
        //setam driverul de chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
        //deschidem un browser de chrome
        driver = new ChromeDriver();
        //accesam un url
        driver.get("http://demo.automationtesting.in/Register.html");
        //intra in  modul maximese
        driver.manage().window().maximize();
        //Facem refresh la pagina
        driver.navigate().refresh();
        //validam pagina pe care ne aflam
        //de fiecare data cand intram pe o pagina trebuie sa o validam
        String ExpectedRegisterPageTitle = "Register";
        String ActualRegisterPageTitle = driver.getTitle();
        Assert.assertEquals("Pagina Register nu are un titlu corect", ExpectedRegisterPageTitle, ActualRegisterPageTitle);

        //ca sa identificam un web element trebuie sa ii gasim selectorul comun
        //un web element se poate identifica dupa: id,class.orice atribut unic(xpath)
        //structura xpath: //am cautat primul cuvant(cel cu mov)[@selector="valoare"]=> 1 din 1
        //1.identific element
        //2.specific actiunea
        WebElement FirstNameWeb = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
        String FirstNameValue = "Sabina";
        FirstNameWeb.sendKeys(FirstNameValue);
        WebElement LastNameWeb = driver.findElement((By.xpath("//input[@placeholder='Last Name']")));

        String LastNameValue = "Luca";
        LastNameWeb.sendKeys(LastNameValue);
        WebElement GenderWeb = driver.findElement(By.xpath("//input[@value='Male']"));
        GenderWeb.click();
        WebElement HobbyWeb = driver.findElement(By.id("checkbox2"));
        HobbyWeb.click();

        WebElement SkillsWeb = driver.findElement(By.id("Skills"));
        String SkillsValue = "Java";
        Select SkillsSelect = new Select(SkillsWeb);
        SkillsSelect.selectByVisibleText(SkillsValue);

        WebElement CountriesWeb = driver.findElement(By.id("countries"));
        String CountriesValue = "Algeria";
        Select CountriesSelect = new Select(CountriesWeb);
        CountriesSelect.selectByVisibleText(CountriesValue);

        WebElement YearWeb = driver.findElement(By.id("yearbox"));
        String YearValue = "1918";
        Select YearSelect = new Select(YearWeb);
        YearSelect.selectByVisibleText(YearValue);

        //inchid browserul la finalul testului
        //driver.quit();

        //driver.close inchide tabul curent
        //driver.quit inchide browserul cu toate taburile
        //TEMA: de validat web Table
        //dau click pe meniul de webtable+ validez pagina de web table

        //WebElement AddressWeb=driver.findElement(By.id("Adress"));
        //String AddressValue="Miko IMRE";
        //Select AddressSelect=new Select(AddressWeb);
        // AddressSelect.selectByVisibleText(AddressValue);

        //WebElement Emailaddress=driver.findElement(By.xpath("//input[ng-model='EmailAdress"));
        //String EmailaddressValue=""+System.currentTimeMillis()+"blabla.com";
        //Un drop down il recunoastem dupa cuvantul "select" din codul html

        //Un drop down  care nu are cuvantul 'select' din codul HTML este format din 2 componete
        //1. componenta pe care dam click
        WebElement LanguagesWeb = driver.findElement(By.id("msdd"));
        LanguagesWeb.click();
        //2. componenta pe care selectam varianta dorita
        List<WebElement> LanguagesOptionList = driver.findElements(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all']//a"));
        for (int Index = 0; Index < LanguagesOptionList.size(); Index++) {
            String CurrentElementText = LanguagesOptionList.get(Index).getText();
            if (CurrentElementText.equals("Romanian")) {
                LanguagesOptionList.get(Index).click();
            }
        }
        GenderWeb.click();

        WebElement SelectCountryWeb = driver.findElement(By.xpath("//span[@role='combobox']"));
        SelectCountryWeb.click();
        List<WebElement> SelectCountryOptionsList = driver.findElements(By.xpath("//ul[@id='select2-country-results']/li"));
        for (int Index = 0; Index < SelectCountryOptionsList.size(); Index++) {
            String CurrentElementText = SelectCountryOptionsList.get(Index).getText();
            if (CurrentElementText.equals("Australia")) {
                SelectCountryOptionsList.get(Index).click();
                break;
            }
        }   //Asa se face ChooseFile!Atentie aici folosesc sendKeys + path-ul cu fisieru;
        //Path-ul in Windows se face cu \\ iar in IOS cu un singur / sau \
        //Path-ul: Click dreapta pe fisier> Properties>Security>apare path-ul
        //Pot sa faca upload fisier daca elementul are atribut/valoare imagesrc sau ceva metoda specifica de upload/import .sendKeys accepta


        WebElement SelectChooseFileWeb = driver.findElement(By.id("imagesrc"));
        SelectChooseFileWeb.sendKeys("C:\\Users\\Sabina D. Luca\\Desktop\\test.docx");
        //TEMA: de validat web Table
         //dau click pe meniul de webtable+ validez pagina de web table
        //incer dupa ce am dat click pe buton sa fac refresh la pagina si apoi sa validez pagina
        //daca nu merge,ii spun sa mearga la un anumit URL
        WebElement WebTabelWeb=driver.findElement(By.xpath("//a[@href='WebTable.html']"));
        WebTabelWeb.click();
        driver.navigate().to("http://demo.automationtesting.in/WebTable.html");
        String ExpectedWebTablePageTitle="Web Table";
        String ActualWebTablePageTitle=driver.getTitle();
        Assert.assertEquals("Pagina WebTable nu are titlu corect.",ExpectedWebTablePageTitle,ActualWebTablePageTitle);
        driver.quit();


    }
}


