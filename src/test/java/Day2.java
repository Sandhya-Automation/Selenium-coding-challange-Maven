import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    static WebDriver driver;
    static String recommended="Recommended for you";
    @Test
    public void getData() throws InterruptedException {
        driver=new FirefoxDriver();
        driver.get("https://www.noon.com/uae-en/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));



        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,800)");
        //WebElement element=driver.findElement(By.xpath("//h2[@class='truncate-title-header' and text()='Recommended for you']"));
        //System.out.println(element.getText());
        // js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[text()='Recommended for you']")));

        getCarousalData(recommended);

        // driver.findElement(By.xpath("//h2[text()='Recommended for you']//parent::div//parent::div//following-sibling::div/div[2]")).click();


    }
    public static void getCarousalData(String recommended) throws InterruptedException {
        WebElement element=driver.findElement(By.xpath("//h2[@class='truncate-title-header' and text()='Recommended for you']"));
        //System.out.println(element.getText());

        int count=0;
        int sum=0;

        List<WebElement> recomspanlist=driver.findElements(By.xpath("//h2[text()='Recommended for you']//parent::div//parent::div//following-sibling::div//div[@data-qa='product-name']/span"));
        System.out.println("total products in this carousal are:"+recomspanlist.size());
        List<String> itemslist = new ArrayList<>();
        for(WebElement w:recomspanlist){
            count++;
            sum++;
            itemslist.add(String.valueOf(w.getText()));
            //   System.out.println("-------------------------------");
            // System.out.println("The "+sum+" product in carousal");
            // System.out.println(w.getText());
            // Thread.sleep(2000);

            if(count==6)
            {

                driver.findElement(By.xpath("//h2[text()='Recommended for you']//parent::div//parent::div//following-sibling::div/div[2]")).click();
                count=0;
                //  System.out.println("===============================");
                //   System.out.println("end of carousal click on next");
                Thread.sleep(1000);
            }
        }

        //  List<String> orignallist=recomspanlist.stream().map(s->s.getText()).collect(Collectors.toList());

        List<String> sortedlist=itemslist.stream().sorted().collect(Collectors.toList());
        for(String s:sortedlist){
            System.out.println(s);
        }




    }
}