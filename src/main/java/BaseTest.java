import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 20;
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

    }

    @Test(dependsOnMethods = "checkListOfBt", description = "create new BT")
    public void createNewBt() {
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
        By COLLAPSE_LOCATOR = By.xpath("//span[text()='Collapse']");
        Actions actions = new Actions(driver);

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
        if ( !driver.findElement(By.xpath("//*[@id='ticketsRequired']")).isSelected() ){
            driver.findElement(By.xpath("//*[@id='ticketsRequired']")).click();
        }
        if ( !driver.findElement(By.xpath("//*[@id='carRequired']")).isSelected() ){
            driver.findElement(By.xpath("//*[@id='carRequired']")).click();
        }
        driver.findElement(By.xpath("//input[@class='textfield textfieldDigit textfieldAmount' and @name='estimatedBudget']"))
                .sendKeys(estimatedBudget.toString());
//        waitForElementEnabled(COLLAPSE_LOCATOR);
//        actions.moveToElement(driver.findElement(COLLAPSE_LOCATOR)).click().perform();

        driver.findElement(By.xpath("//input[@id='plannedStartDate_ui']"))
                .sendKeys(plannedStartDate);
        driver.findElement(By.xpath("//input[@id='plannedEndDate_ui']"))
                .sendKeys(plannedEndDate);
//        String textDate = driver.findElement(By.xpath("//input[@id='plannedEndDate_ui']")).getAttribute("value").toString();
//        System.out.println(textDate);
        //Проверить что обязательные поля заполнены
//
//        WebElement tickets = driver.findElement(By.xpath("//*[@id='ticketsRequired']"));
//        Actions actions1 = new Actions(driver);
//        actions1.moveToElement(tickets).click().perform();


//        if ( !driver.findElement(By.xpath("//*[@id='ticketsRequired']")).isSelected() ){
//            driver.findElement(By.xpath("//*[@id='ticketsRequired']")).click();
//        }


    }

    @Test(dependsOnMethods = "createNewBt", description = "Save BT")
    public void SaveItem() throws InterruptedException {
        By SAVE_LOCATOR = By.xpath("//*[@id='saveButton']/button");
        By formLocker = By.xpath("//*[@id='formLocker']");
//        Actions actions = new Actions(driver);
//        waitForElementEnabled(SAVE_LOCATOR);
//        actions.moveToElement(driver.findElement(SAVE_LOCATOR)).click().perform();

//        fluentWait(SAVE_LOCATOR);
//        waitForFormLockerDisappear(driver, formLocker);
        Thread.sleep(20000);
        waitForElementClickable(driver, driver.findElement(SAVE_LOCATOR));
        Thread.sleep(20000);
        driver.findElement(SAVE_LOCATOR).click();
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
//        Thread.sleep(10000);
//        WebElement saveButton = driver.findElement(By.xpath("//div[@id='toolbarBottom']//div[@id='saveButton']/button"));
//        Actions actions2 = new Actions(driver);
//        actions2.moveToElement(saveButton).click().perform();

//        WebElement element = driver.findElement(By.xpath("//div[@id='toolbarBottom']//div[@id='saveButton']/button"));
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("arguments[0].click()", element);
    }

    @Test(dependsOnMethods = "SaveItem", description = "modify BT")
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
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1600, 900));
        baseUrl = "https://tst1.epm-ctc.projects.epam.com/";
        driver.get(baseUrl + "/login.do?logout=true&tz=GMT%2B06:00");
        driver.findElement(By.xpath("//input[@name='username']"))
                .sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']"))
                .sendKeys(pwdName);
        driver.findElement(By.xpath("//input[@name='Login']"))
                .click();
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    protected void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        return  foo;
    };

    public static void waitForElementClickable (WebDriver driver, WebElement element){
        FluentWait<WebDriver> wait = new WebDriverWait(driver, 5);
        wait.until((ExpectedCondition<Object>) driver1 -> {
            try {
                element.click();
                return true;
            } catch (WebDriverException e) {
                return false;
            }
        });
    }

    private boolean pageStartsLoading(int timeout, WebDriver driver, String locator) {
        WebElement element = null;
        By loadingIndicatorLocator = By.xpath(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(loadingIndicatorLocator));
        return element != null;
    }

    private void waitUntilPageStopsLoading(int timeout, WebDriver driver, String locator) {
        By loadingIndicatorLocator = By.xpath(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIndicatorLocator));
    }

    public static void waitForElementDisappear(WebElement element, WebDriver driver) {
        try {
            element.getText();
            element.getAttribute("id");
        } catch (NoSuchElementException e) {
            return;
        }
        FluentWait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.ignoring(TimeoutException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }


    public static void waitForFormLockerDisappear(WebDriver driver,By locator) {
        WebElement element = driver.findElement(locator);
        waitForElementDisappear(element, driver);
    }

}
