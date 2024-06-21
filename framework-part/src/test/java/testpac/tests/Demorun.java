package testpac.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;
import testpac.Testcomponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Demorun extends BaseTest {
   public Productcatalog productRef;
   public ProductCheckPage ProductcheckRef;
    @Test(dataProvider = "getData",groups = {"purchase"})
        public void fun(HashMap<String,String> input) throws InterruptedException, IOException {
            loggingIn(input.get("email"), input.get("password"));
            productRef = new Productcatalog(driver);
            List<WebElement> product = productRef.getListOfProduct();
            List<WebElement> filterdItems =productRef.filterTheItems(product);
            productRef.click(filterdItems);
            productRef.clickCart();

            ProductcheckRef = new ProductCheckPage(driver);
            List<WebElement> insidecart = ProductcheckRef.itemListInsideCart();
            boolean contains = ProductcheckRef.toCheckItemPresent(insidecart);
            Assert.assertTrue(contains);
            CountrySelector Cs = ProductcheckRef.clickonTotalButton();// creating CountrySelector
            //object inside the ProductCheckPage
            Cs.selectCountryField();
            Cs.clickCountry();
            Cs.SubmitButton();

        }


    @Test(dependsOnMethods = {"fun"},dataProvider = "getData")
    //@Test(dataProvider = "getData")
    public void Checkorder(HashMap<String,String> input) throws IOException, InterruptedException {
        loggingIn(input.get("email"), input.get("password"));
        List<WebElement> product = productRef.getListOfProduct();
        List<WebElement> filterdItems =productRef.filterTheItems(product);
        productRef.click(filterdItems);
        OrderPage p = new OrderPage(driver);
        Assert.assertTrue(p.perform("ZARA COAT 3"));

    }
    @Test(dependsOnMethods = {"Checkorder"},dataProvider = "getData")
    public void itemcheck(HashMap<String,String> input) throws InterruptedException, IOException {
        loggingIn(input.get("email"), input.get("password"));
        productRef = new Productcatalog(driver);
        List<WebElement> product = productRef.getListOfProduct();
        List<WebElement> filterdItems =productRef.filterTheItems(product);
        productRef.click(filterdItems);
        productRef.clickCart();

        ProductcheckRef = new ProductCheckPage(driver);
        List<WebElement> insidecart = ProductcheckRef.itemListInsideCart();
        boolean contains = ProductcheckRef.toCheckItemPresent(insidecart);
        Assert.assertTrue(contains);
    }

    /*@DataProvider
    public Object[][] getData() {
        return new Object[][]{{"bheeshmacharya@gmail.com", "Bheeshma@123"}, {"pitamaha@gmail.com", "Bheeshma@123"}};

    }*/
    /*@DataProvider
    public Object[][] getData() {

        HashMap<String,String> map1 = new HashMap<String, String>();
        map1.put("email","bheeshmacharya@gmail.com");
        map1.put("password","Bheeshma@123");
        HashMap<String,String> map2 = new HashMap<String, String>();
        map2.put("email","pitamaha@gmail.com");
        map2.put("password","Bheeshma@123");
        return new Object[][] {{map1},{map2}};

    }*/

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = dataReader(System.getProperty("user.dir")+"//src//test//java//testpac//data//purchaseOrder.json");
        return new Object[][] {{data.get(0)}};
    }


}



