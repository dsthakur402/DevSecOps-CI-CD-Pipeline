package com.example;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AppTest {
        WebDriver driver = new ChromeDriver();
    static ExtentTest test;
    static ExtentReports report;
     
    

    @BeforeClass
    public static void startTest() {
        report = new ExtentReports(System.getProperty("user.dir") + "ExtentReportResults.html");
        test = report.startTest("ExtentDemo");
    }

    @Test
    public void extentReportsDemo() throws InterruptedException {
        
        driver.get("http://localhost:3000/");
        System.out.println(driver.getTitle());
      
            if (driver.getTitle().equals("ToDo App")){
                
                test.log(LogStatus.PASS, "Navigated to the specified URL");
            } else {
                test.log(LogStatus.FAIL, "Test Failed - Unexpected Page Title");
            }

        Thread.sleep(2000);
        WebElement elements = driver.findElement(By.xpath("//input[@type='text']"));
        elements.sendKeys("john");
       
        WebElement adds = driver.findElement(By.xpath("//button[text()='Add']"));
        
        adds.click();
        
        
         // Add new user
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//input[@type='text']"));
        element.sendKeys("john");
        WebElement add = driver.findElement(By.xpath("//button[text()='Add']"));
        // if(driver.getTitle().equals("john"))
        // {
        // test.log(LogStatus.PASS, "Test Passed");
        // }
        // else
        // {
        // test.log(LogStatus.FAIL, "Test Failed");
        // }
        add.click();
        Thread.sleep(2000);
        WebElement editIcon = driver.findElement(By.cssSelector("svg.icon1"));
        editIcon.click();

        // Edit the user
        Thread.sleep(2000);
        WebElement edit = driver.findElement(By.xpath("//div//input[@type='text' and @placeholder='Update ToDo...']"));
        edit.clear();
        edit.sendKeys("rahul");

        // Click on update button
        Thread.sleep(1000);
        WebElement update = driver.findElement(By.xpath("//div//button[text()='Update']"));
        update.click();
        Thread.sleep(2000);
        WebElement removeIcon = driver.findElement(By.cssSelector("svg.icon2"));
        removeIcon.click();
         driver.close();
    }

    @AfterClass
    public static void endTest() {
        
        report.endTest(test);
        report.flush();
    }
}
