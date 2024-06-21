package pageobjects;

import Abstractpac.Abstractclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends Abstractclass {

    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//tr/td[2]")
    List<WebElement> orderList;
    By findBy = By.cssSelector("[class*='container']");

    public boolean perform(String value)
    {
        clickOrderButton();
        elementToLoad(findBy);
        boolean val =orderList.stream().filter(s->s.getText().equals(value)).anyMatch(s->
                s.getText().equals(value));
        return val;
    }


}
