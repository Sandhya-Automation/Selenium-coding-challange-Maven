import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FilterHandling_ecommerce {
    static WebDriver driver;

    @Test
    public void setFilters() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.t-mobile.com/commerce/tablets");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,300)", new Object[0]);
        selectFilter("Deals", "New");
        selectFilter("Brands", "Apple", "Samsung");
        selectFilter("Operating system", "Android");
    }

    private static void selectFilter(String... lists) throws InterruptedException {
        for(int i = 1; i < lists.length; ++i) {
            //System.out.println(lists[i]);
            Thread.sleep(2000L);
            System.out.println(driver.findElement(By.xpath("//span[text()='" + lists[i] + "']")).getText());
            driver.findElement(By.xpath("//span[text()='" + lists[i] + "']")).click();
        }

    }
}

