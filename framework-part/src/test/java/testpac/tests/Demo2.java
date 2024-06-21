package testpac.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo2 {

        public static void main(String[] args) throws InterruptedException {

            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
            driver.get("https://rahulshettyacademy.com/client");
            driver.findElement(By.id("userEmail")).sendKeys("bheeshmacharya@gmail.com");
            driver.findElement(By.id("userPassword")).sendKeys("Bheeshma@123");
            driver.findElement(By.id("login")).click();
            w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='mb-3']")));
            List<WebElement> product =driver.findElements(By.cssSelector("div[class*='mb-3']"));
            List<String> items = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");
            List<WebElement> filterdItems = product.stream()
                    .filter(p -> {
                        String text = p.findElement(By.tagName("b")).getText();
                        return items.stream().anyMatch(text::contains);
                    }).collect(Collectors.toList());
            for(WebElement Items:filterdItems) {
                Items.findElement(By.cssSelector("button:last-of-type")).click();
                Thread.sleep(5000);
            }
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

            List<WebElement> insidecart =driver.findElements(By.cssSelector(".cart h3"));
            Assert.assertTrue(insidecart.stream().anyMatch(prod->{String st = prod.getText();
            return items.stream().anyMatch(st::contains);}));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1300)");
            //w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
            //driver.findElement(By.xpath("//button[text()='Checkout']").click();
            WebElement button = driver.findElement(By.xpath("//div[contains(@class,'subtotal')]//button"));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", button);
            w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
            driver.findElement(By.xpath("//input[@placeholder='Select Country']")).click();
            Actions a  = new Actions(driver);
            a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
            //w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
            driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
            driver.findElement(By.cssSelector(".action__submit")).click();

        }

    }



