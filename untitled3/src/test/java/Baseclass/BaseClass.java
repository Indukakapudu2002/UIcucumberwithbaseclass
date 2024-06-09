package Baseclass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
    protected WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest link_test;
    public static ExtentSparkReporter spark;

    // Common setup
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        System.out.println("Logged into url");
        driver.manage().window().maximize();
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/index1.html");
        extent.attachReporter(spark);
        link_test = extent.createTest("test");
    }
    public WebDriver getDriver() {
        return driver;
    }
    public ExtentTest getLink_test()
    {
        return link_test;
    }
}


