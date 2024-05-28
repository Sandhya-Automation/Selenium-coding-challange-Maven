import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;

public class Day1 {
    static String totalpopulation = "";
    @Test
    public void findPopulation() throws InterruptedException {
        System.out.println("hello world");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.worldometers.info/world-population/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.className("fc-cta-manage-options")).click();
        driver.findElement(By.className("fc-confirm-choices")).click();
        System.out.println("Current world population is:");
        // List<Object> list=new ArrayList<Object>();
        List<WebElement> spanlist=driver.findElements(By.xpath("//div[@class='maincounter-number']/span"));
        totalpopulation=getPopulationNumber(spanlist);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,600)");

        //getTodayPopulation(driver);
        //  getThisYearPopulation(driver);
        // Thread.sleep(20000);
        //  getTodayPopulation(driver);
        //  getThisYearPopulation(driver);
        LocalDateTime nowTime=LocalDateTime.now();
        while(true){
            if(ChronoUnit.SECONDS.between(nowTime,LocalDateTime.now())>=20) break;
            else{
                getTodayPopulation(driver);
                getThisYearPopulation(driver);
            }

        }

    }
    public static String getPopulationNumber(List<WebElement> span){

        String totalpop="";
        for(WebElement w:span)
        {
            if(w.getText()!=null){
                totalpop+=w.getText();
                totalpop=totalpop+"\n";
            }

        }
        System.out.println(totalpop);
        return totalpop;
    }
    public static void getTodayPopulation(WebDriver driver) {
        System.out.println("Today's population:");
        //List<WebElement> todaysnumber = driver.findElements(By.xpath("//div[@class='col1in']/div/div/span"));
        List<WebElement> todaysnumber = driver.findElements(By.xpath("//div[@class='col1in']/div[position()>1]"));
        for(WebElement w: todaysnumber){

            System.out.println(w.getText());


        }

    }
    public static void getThisYearPopulation(WebDriver driver) {
        System.out.println("This Year population:");
        //List<WebElement> todaysnumber = driver.findElements(By.xpath("//div[@class='col1in']/div/div/span"));
        List<WebElement> todaysnumber = driver.findElements(By.xpath("//div[@class='col2in']/div[position()>1]"));
        for(WebElement w: todaysnumber){

            System.out.println(w.getText());


        }

    }

}