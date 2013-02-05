import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class that all test cases using WebDriver should extend. 
 */
@RunWith(JUnit4.class)
public abstract class CharityWareBaseTestCase {
	protected static WebDriver driver;
	
	@BeforeClass
	static public void setupBrowser() {
		driver = new FirefoxDriver();
	}
	
	@AfterClass
	static public void shutdownBrowser() {
		driver.quit();
	}
	
	protected void openInternalPage(String absolutePath) {
		driver.get(Settings.TARGET_DOMAIN + absolutePath);
	}
	
	protected WebDriverWait getDefaultWait() {
		return new WebDriverWait(driver, Settings.DEFAULT_WAIT_DURATION_SEC);
	}
}
