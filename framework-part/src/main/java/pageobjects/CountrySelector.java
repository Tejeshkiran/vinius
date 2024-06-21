package pageobjects;

import Abstractpac.Abstractclass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.xml.xpath.XPath;

public class CountrySelector extends Abstractclass {

    WebDriver driver;
    public CountrySelector(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement Country;
    By findBy = By.xpath("//input[@placeholder='Select Country']");

    public void selectCountryField() throws InterruptedException {
        //elementToLoad(findBy);
        Thread.sleep(2000);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();",Country);
        //Country.click();
        Actions a  = new Actions(driver);
        a.sendKeys(Country,"India").build().perform();
        //w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
    }
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;
    public void clickCountry()
    {
        selectCountry.click();
    }
    @FindBy(css = ".action__submit")
    WebElement Submitbutton;
    public void SubmitButton()
    {
        //Submitbutton.click();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();",Submitbutton);
    }

}
