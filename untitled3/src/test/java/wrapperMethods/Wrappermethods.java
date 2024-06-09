package wrapperMethods;
import org.openqa.selenium.*;
import java.io.File;
import java.time.Duration;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Wrappermethods
{
    private static WebDriver driver;
    @FindBy(xpath = "//button[@class='btn btn-primary' and text()='Home']")
    private static  WebElement homepage;
    @FindBy(xpath = "//div[@class='logo']//img[@src = 'assets/images/rs_logo.png']")
    private static WebElement homelogo;
    @FindBy(xpath = "//a[@href=\"https://courses.rahulshettyacademy.com/sign_in\"]")
    private static WebElement loginButton;
    @FindBy(xpath = "//input[@id='email']")
    private static WebElement usernameField;
    @FindBy(xpath = "//input[@id='password']")
    private static WebElement passwordfield;
    @FindBy(xpath = "//input[@value='Log in']")
    private static WebElement loginclick;
    @FindBy(xpath = "//input[@class='form-control search input-lg']")
    private static WebElement searchfield ;
    @FindBy(xpath = "(//a[@class = 'fedora-navbar-link navbar-link'])[2]")
    private static WebElement courseslist ;
    @FindBy(xpath = "//div[@class='course-listing-title' and @title='All-Access Membership']")
    private static WebElement searchresult;
    @FindBy(xpath = "//input[@value='radio1']")
    private static WebElement radioButton1;
    @FindBy(xpath = "//input[@value='radio2']")
    private static WebElement radioButton2;
    @FindBy(xpath = "//input[@value='radio3']")
    private static WebElement radioButton3;
    @FindBy(xpath = "//input[@id='autocomplete']")
    private static WebElement inputvalidation;
    @FindBy(xpath = "//select[@id='dropdown-class-example']")
    private static WebElement dropdowns;
    @FindBy(xpath = "//input[@id='checkBoxOption1']")
    private static WebElement checkbox1;
    @FindBy(xpath = "//input[@id='checkBoxOption2']")
    private static WebElement checkbox2;
    @FindBy(xpath = "//input[@id='checkBoxOption3']")
    private static WebElement checkbox3;
    @FindBy(xpath = "//button[@id='openwindow']")
    private static WebElement switchwindow;
    @FindBy(xpath = "//a[@id='opentab']")
    private static WebElement switchtab;
    @FindBy(xpath = "//input[@id='alertbtn']")
    private static WebElement switchalert;

    public Wrappermethods(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public  boolean homePageValidation() throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homepage.click();
        wait.until(ExpectedConditions.visibilityOf(homelogo));
        return homelogo.isDisplayed();
    }
    public static String getScreenshot(WebDriver driver) throws Exception {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        String dest="screenshots/screenshot"+System.currentTimeMillis()+".png";
        File destination=new File(dest);
        FileUtils.copyFile(source,destination);
        return dest;
    }
    public  boolean loginToApplication(String username, String password)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.visibilityOf(loginButton));
//        loginButton.click();
        driver.get("https://courses.rahulshettyacademy.com/sign_in");
        usernameField.sendKeys(username);
        passwordfield.sendKeys(password);
        loginclick.click();
        wait.until(ExpectedConditions.visibilityOf(searchfield));
        return searchfield.isDisplayed();
    }
    public boolean searchcourses() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        Thread.sleep(200);
//        courseslist.click();
        driver.get("https://courses.rahulshettyacademy.com/courses/");
        searchfield.sendKeys("All-Access Membership");
        searchfield.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(searchresult));
        return searchresult.isDisplayed();
    }
    public boolean handleradiobutton()
    {
        List<WebElement> radioButtons = new ArrayList<>();
        radioButtons.add(radioButton1);
        radioButtons.add(radioButton2);
        radioButtons.add(radioButton3);
        Random random = new Random();
        WebElement randomradiobutton = radioButtons.get(random.nextInt(radioButtons.size()));
        randomradiobutton.click();
        return randomradiobutton.isSelected();
    }
    public boolean inputhvalidations()
    {
        inputvalidation.sendKeys("testing project");
        return inputvalidation.isEnabled();
    }
    public  boolean handledropdowns()
    {
        Select dropdown = new Select(dropdowns);
        dropdown.selectByIndex(2);
//        dropdown.selectByVisibleText("Option 1");
//        dropdown.selectByValue("value1");
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        System.out.println("Selected option: " + selectedOption.getText());
        return true;
    }
    public boolean checkboxvalidation()
    {
        List<WebElement> checkboxes = new ArrayList<>();
        checkboxes.add(checkbox1);
        checkboxes.add(checkbox2);
        checkboxes.add(checkbox3);
        Random random = new Random();
        WebElement randomcheckbox= checkboxes.get(random.nextInt(checkboxes.size()));
        randomcheckbox.click();
        return randomcheckbox.isSelected();
    }
    public String switchwindow()
    {
        switchwindow.click();
        Set<String> allWindowHandles = driver.getWindowHandles();
        String mainWindowHandle = driver.getWindowHandle();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        return driver.getTitle();
    }
    public String switchtab()
    {
        switchtab.click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        System.out.println("All window handles: " + allWindowHandles);
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        return driver.getTitle();
    }
    public String switchalert()
    {
        switchalert.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

}
