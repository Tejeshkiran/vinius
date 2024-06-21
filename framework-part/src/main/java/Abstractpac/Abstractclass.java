package Abstractpac;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Abstractclass {

    public WebDriver driver;
    public WebDriverWait w;
    public Abstractclass(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void elementToLoad(By findby) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        w = new WebDriverWait(driver,Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(findby));
    }
    public void visiblityOfElement(WebElement erromessage)
    {
        w = new WebDriverWait(driver,Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOf(erromessage));
    }
    public List<String> itemsList(String item1, String item2, String item3){
        List<String> items = Arrays.asList(item1, item2, item3);
        return items;
    }
    public List<String> itemsList(String item1, String item2){
        List<String> items = Arrays.asList(item1, item2);
        return items;
    }
    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderbutton;

    public void clickOrderButton()
    {
        orderbutton.click();
    }


}
