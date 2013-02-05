import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Test class for login.jsp
 */
public class LoginTest extends CharityWareBaseTestCase {
	private static final String TARGET_PAGE = "login.jsp";
	
	@Before
	public void openLoginJsp() {
		openInternalPage(TARGET_PAGE);
	}
	
	@Test
	public void testLoginFailureWithWrongUserNameAndPassword() {
		WebElement userNameInput = driver.findElement(By.id("txtUsername"));
		WebElement passwordInput = driver.findElement(By.id("txtPassword"));
		
		userNameInput.sendKeys("not-exist");
		passwordInput.sendKeys("not-exist");
		
		WebElement submitButton = driver.findElement(By.id("button1"));
		submitButton.click();
		
		WebDriverWait wait = getDefaultWait();
		wait.until(new ExpectedCondition<Boolean>(){
			
			@Override
			public Boolean apply(WebDriver d) {
				WebElement userNameInput = driver.findElement(By.id("txtUsername"));
				return userNameInput.getText().length() == 0;
			}});
	}
}
