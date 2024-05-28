import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Day4 {
    static WebDriver driver;
    static String country = "California";

    public Day4() {
    }

    @Test
    public void clickCountry() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://petdiseasealerts.org/forecast-map#/");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        Thread.sleep(3000L);
        js.executeScript("window.scrollBy(0,400)", new Object[0]);
        System.out.println(driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame(0);
        String str = driver.findElement(By.className("breadcrumb-alt")).getText();
        List<WebElement> listofregions = driver.findElements(By.xpath("//*[@id='features']//*[@id='regions']/*[name()='g']"));
        System.out.println(listofregions.size());
        System.out.println(str);
        searchCountry(listofregions, country);
    }

    public static void searchCountry(List<WebElement> lists, String country) {
        int region = 0;
        Iterator var3 = lists.iterator();

        while(var3.hasNext()) {
            WebElement w = (WebElement)var3.next();
            ++region;
            PrintStream var10000 = System.out;
            String var1 = w.getAttribute("id");
            if (w.getAttribute("id").equalsIgnoreCase(country)) {
                System.out.println(w.getAttribute("id"));
                driver.findElement(By.cssSelector("#region-" + region)).click();
                break;
            }
        }

    }
}
