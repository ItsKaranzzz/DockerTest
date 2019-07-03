import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleTest {
    WebDriver driver;
    DesiredCapabilities cap;

    @Parameters({"browser"})
    @BeforeClass
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
        // String URL = Utils.getConfigs().getProperty("Test_Url");
        driver.get("http://www.google.com");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Google";
        Assert.assertEquals(actualTitle, expectedTitle, "Title was not " + expectedTitle);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
