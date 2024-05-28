import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {
    static WebDriver driver;
    static List<Object> sortedlist =new ArrayList<>();
    String highestprice="";
    @Test
    public void getHighestPrice() {
        driver=new FirefoxDriver();
        driver.get("https://www.saucedemo.com/inventory.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        String username="standard_user";
        String password="secret_sauce";
        login(username,password);
        addtocart();


    }



    private static void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

    }
    private static void addtocart() {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        List<String> listprice=new ArrayList<>();
        List<WebElement> pricelists=driver.findElements(By.cssSelector(".inventory_item_price"));

        double maxprice=pricelists.stream().mapToDouble(e->Double.parseDouble(e.getText().trim().replace("$",""))).max().getAsDouble();
        System.out.println("Max is "+maxprice);
//        for(WebElement w:pricelists)
//        {
//            String justprice=w.getText().substring(1);
//            listprice.add(justprice);
//           // System.out.println(justprice);
//        }
//         sortedlist=listprice.stream().map(Runtime.Version::parse).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
//            for(Object o:sortedlist){
//                System.out.println(o);
//
//            }
        driver.findElement(By.xpath("//div[text()='"+maxprice+"']//following-sibling::button")).click();


//div[text()='49.99']//following-sibling::button
    }
}
