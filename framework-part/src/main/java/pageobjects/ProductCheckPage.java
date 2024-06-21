package pageobjects;

import Abstractpac.Abstractclass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCheckPage extends Abstractclass {
    WebDriver driver;

    public ProductCheckPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart h3")
    List<WebElement> insidecart;

    public List<WebElement> itemListInsideCart() {
        return insidecart;
    }

    public boolean toCheckItemPresent(List<WebElement> insidecart) {
        List<String> items = itemsList("ADIDAS ORIGINAL","IPHONE 13 PRO");
        boolean contain = items.stream().allMatch(item ->
                insidecart.stream().anyMatch(prod -> item.equals(prod.getText().trim()))
        );
        for(WebElement inside : insidecart)
        {
            System.out.print(inside.getText()+ " ");
        }
        System.out.println(contain);
        return contain;

    }

    @FindBy(xpath = "//div[contains(@class,'subtotal')]//button")
    WebElement button;

    public CountrySelector clickonTotalButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1300)");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", button);
        return new CountrySelector(driver);
    }
}
