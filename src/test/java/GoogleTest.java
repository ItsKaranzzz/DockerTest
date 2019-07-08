import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleTest {
    WebDriver driver;
    DesiredCapabilities cap;
    String URL = Utils.getConfigs().getProperty("Test_Url");

    @Parameters({"browser"})
    @BeforeTest
    public void setUp(String browser) throws MalformedURLException {
        if (browser.equalsIgnoreCase("chrome")) {
            cap = new DesiredCapabilities();
            cap.setBrowserName("chrome");
            cap.setPlatform(Platform.LINUX);
            WebDriverManager.chromedriver().setup();
            driver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), cap);
        } else if (browser.equalsIgnoreCase("firefox")) {
            cap = new DesiredCapabilities();
            cap.setBrowserName("firefox");
            cap.setPlatform(Platform.LINUX);
            WebDriverManager.firefoxdriver().setup();
            driver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), cap);
        }


    }

    @Test(threadPoolSize = 2)
    public void verifyTitle() {
        driver.get(URL);
        String actualTitle = driver.getTitle();
        String expectedTitle = "Google";
        Assert.assertEquals(actualTitle, expectedTitle, "Title was not " + expectedTitle);
    }

    @Test
    public void verifySearchedLanguageOptions() {
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement searchBtn = driver.findElement(By.id("SIvCob"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        String actualSearchBtnText = searchBtn.getText();
        Assert.assertEquals(actualSearchBtnText, "Google offered in: हिन्दी বাংলা తెలుగు मराठी தமிழ் ગુજરાતી ಕನ್ನಡ മലയാളം ਪੰਜਾਬੀ", "Search Languages shown didn't match");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
