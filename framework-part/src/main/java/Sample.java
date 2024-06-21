import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Sample {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://colab.research.google.com/#scrollTo=C4HZx7Gndbrh");
       // driver.findElement(By.xpath("//span[@class='gb_Kd']")).click();
        //driver.findElement(By.xpath("//input[@type='email']")).sendKeys("tejeshkirant@gmail.com");
        //driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d']")).click();
        System.out.println("first time change");
        System.out.println("IST change first time");
    }
}

