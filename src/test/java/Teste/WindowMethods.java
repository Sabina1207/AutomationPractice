package Teste;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class WindowMethods {
     public WebDriver driver;
     public WindowMethods(WebDriver driver){
         this.driver=driver;
     }
     public void SwitchToSpecificTabWindow(int Valoare){
         List<String>OpenTabsWindows=new ArrayList<>(driver.getWindowHandles());
         driver.switchTo().window(OpenTabsWindows.get(Valoare));
         System.out.println("The title of the curent page is "+driver.getTitle());

     }

     public void CloseCurrentTabWindow(){
         driver.close();
     }
}
