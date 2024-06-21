package pageobjects;

import Abstractpac.Abstractclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Productcatalog extends Abstractclass {

    WebDriver driver;
    public Productcatalog(WebDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css ="div[class*='mb-3']")
    List<WebElement> productList; // product list locators
    By productBy = By.id("products");// loactors to wait for the element
    public List<WebElement> getListOfProduct()
    {
       // elementToLoad(productBy);
        return productList;
    }
    By tagname = By.tagName("b");
    public List<WebElement> filterTheItems(List<WebElement> product)
    {

        List<String> items = itemsList("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO");
        List<WebElement> filterdItems = product.stream()
                .filter(p -> {
                    String text = p.findElement(tagname).getText();
                    return items.stream().anyMatch(text::contains);
                }).collect(Collectors.toList());
        return filterdItems;

    }
    By clickLocator = By.cssSelector("button:last-of-type");
    public void click(List<WebElement> filterdItems) throws InterruptedException {
        for(WebElement Items:filterdItems) {
            Items.findElement(clickLocator).click();
            Thread.sleep(3000);
        }
    }
    By CartLocator = By.cssSelector("button[routerlink*='cart']");
    public void clickCart() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(CartLocator).click();
    }

}


