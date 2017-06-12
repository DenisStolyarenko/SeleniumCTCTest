import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private final String BUSINESS_TRIP_LOCATION = "/businesstrip/list.do";
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final DateFormat inputFormat = new SimpleDateFormat("MM/dd/yy");
    private WebDriver driver;
    private String baseUrl;

//    @Test(priority = 1)
//    public void loginToCTC(){
//        WebElement user = driver.findElement(By.xpath("//input[@name='username']"));
//        user.sendKeys("dst");
//        WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));
//        pwd.sendKeys("0");
//        WebElement sighin = driver.findElement(By.xpath("//input[@name='Login']"));
//        sighin.click();
//        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
//        //Assert.assertTrue();
//    }

    @Test(priority = 0, description = "check opening the list of Bussiness Trips")
    public void checkListOfBt() {
        driver.get(baseUrl + BUSINESS_TRIP_LOCATION);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test(dependsOnMethods = "checkListOfBt", description = "create new BT")
    public void createNewBt() throws InterruptedException {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        String projectName = "ENRC-TRD";
        String country = "Belarus";
        String destinationCity = "Minsk";
        String destinationAddress = "Minsk";
        String description = "Travel to " + destinationCity + " " + sdf.format(date);
        Integer estimatedBudget = new Random().nextInt(100000);
        String plannedStartDate = "06/11/17"; // исправить на текущую дату inputFormat.format(date)
        String plannedEndDate = "10/11/17"; // исправить на текущую дату плюс 7 дней

        driver.findElement(By.xpath("//input[@title='Create New Business Trip Request']"))
                .click();
        driver.findElement(By.xpath("//img[contains(@onclick,'chooseprojectcostobject')]"))
                .click();
        driver.switchTo().frame("frLookupDialog");
        driver.findElement(By.xpath("//input[@name='keywordSearch']"))
                .sendKeys(projectName);
        driver.findElement(By.xpath("//input[@value='Go']"))
                .click();
        driver.findElement(By.xpath("//input[@type='checkbox' and @projectcostobjectname='" + projectName + "']"))
                .click();
        driver.findElement(By.xpath("//input[@value='OK']"))
                .click();
        driver.findElement(By.xpath("//input[@name='itemName']"))
                .sendKeys("BT created by Selenium " + sdf.format(date));
        Select countrySelect = new Select(driver.findElement(By.xpath(".//select[@name='destinationCountryId']")));
        countrySelect
                .selectByVisibleText(country);
        driver.findElement(By.xpath("//input[@name='destinationCity']"))
                .sendKeys(destinationCity);
        driver.findElement(By.xpath("//textarea[@name='destinationAddress']"))
                .sendKeys(destinationAddress);
        driver.findElement(By.xpath("//textarea[@id='description']"))
                .sendKeys(description);
        driver.findElement(By.xpath("//input[@class='textfield textfieldDigit textfieldAmount' and @name='estimatedBudget']"))
                .sendKeys(estimatedBudget.toString());
        driver.findElement(By.xpath("//input[@id='plannedStartDate_ui']"))
                .sendKeys(plannedStartDate);
        driver.findElement(By.xpath("//input[@id='plannedEndDate_ui']"))
                .sendKeys(plannedEndDate);
//        driver.findElement(By.xpath("//*[@id='ticketsRequired']"))
//                .sendKeys(Keys.ENTER);
//        WebElement tickets = driver.findElement(By.xpath("//*[@id='ticketsRequired']"));
//        Actions actions1 = new Actions(driver);
//        actions1.moveToElement(tickets).click().perform();


//        if ( !driver.findElement(By.xpath("//*[@id='ticketsRequired']")).isSelected() ){
//            driver.findElement(By.xpath("//*[@id='ticketsRequired']")).click();
//        }
        // Click by Save Changes button
//        WebElement saveButton = driver.findElement(By.xpath("//div[@id='toolbarBottom']//div[@id='saveButton']/button"));
//        Actions actions2 = new Actions(driver);
//        actions2.moveToElement(saveButton).click().perform();

//        WebElement collapse = driver.findElement(By.xpath("//span[contains(@onclick, 'collapseFieldDescription')]"));
//        Actions actions3 = new Actions(driver);
//        actions3.moveToElement(collapse).click().perform();
//
//        WebElement saveButton = driver.findElement(By.xpath("//*[@id='saveButton']/button"));
//        Actions actions2 = new Actions(driver);
//        actions2.moveToElement(saveButton).click().perform();
//        List<WebElement> saveButtons = driver.findElements(By.xpath("//*[@id='saveButton']/button"));
//        saveButtons.get(0).click();
        Thread.sleep(10000);
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='toolbarBottom']//div[@id='saveButton']/button"));
        Actions actions2 = new Actions(driver);
        actions2.moveToElement(saveButton).click().perform();

//        WebElement element = driver.findElement(By.xpath("//div[@id='toolbarBottom']//div[@id='saveButton']/button"));
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("arguments[0].click()", element);

    }

    @Test(dependsOnMethods = "createNewBt", description = "modify BT")
    public void modifyBt() {

    }

    @Test(dependsOnMethods = "modifyBt", description = "logout from CTC")
    public void logoutCTC(){
//        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(30, TimeUnit.SECONDS)
//                .pollingEvery(5, TimeUnit.SECONDS)
//                .ignoring(java.util.NoSuchElementException.class);
//
//        WebElement logoutButton = wait.until(new Function<WebDriver, WebElement>() {
//            public WebElement apply(WebDriver webDriver) {
//                return driver.findElement(By.xpath("//a[@href='logout.do']"));
//            }
//        });
//        logoutButton.click();

//        WebElement logoutButton = driver.findElement(By.xpath("//a[@href='logout.do']"));
//        logoutButton.click();
//        Alert alert = driver.switchTo().alert();
//        System.out.println(alert.getText());
//        alert.accept();
    }

    @BeforeClass
    public void beforeClass() {
        String userName = "dst";
        String pwdName = "0";
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1600, 900));
        baseUrl = "https://tst1.epm-ctc.projects.epam.com/";
        driver.get(baseUrl + "/login.do?logout=true&tz=GMT%2B06:00");
        WebElement user = driver.findElement(By.xpath("//input[@name='username']"));
        user.sendKeys(userName);
        WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));
        pwd.sendKeys(pwdName);
        WebElement sighin = driver.findElement(By.xpath("//input[@name='Login']"));
        sighin.click();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }


}
