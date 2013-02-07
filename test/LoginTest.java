import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;


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
        inputValuesAndSubmit("user_name", "wrong_password");

        WebDriverWait wait = getDefaultWait();
        // This method call apply() method in certain time frame until it returns true.
        // If it returns true in given time, it raise exception, so test fails.
        wait.until(new ExpectedCondition<Boolean>(){

            @Override
            public Boolean apply(WebDriver d) {
                WebElement userNameInput = driver.findElement(By.id("txtUsername"));
                return userNameInput.getText().length() == 0;
            }
        });
    }

    @Test
    public void testLoginSuccess() {
        inputValuesAndSubmit("ucladmin", "open");

        WebDriverWait wait = getDefaultWait();
        wait.until(new ExpectedCondition<Boolean>(){

            @Override
            public Boolean apply(WebDriver d) {
                WebElement h2 = driver.findElement(By.tagName("h2"));
                return "UCL Administration Panel".equals(h2.getText());
            }
        });

        // confirm that first tab is initially active: class value of active tab is set to 'active' by JavaScript
        WebElement tab1 = driver.findElement(By.id("tab_1"));
        // assertEquals(expected, actual);
        assertEquals(tab1.getAttribute("className"), "active");
    }

    @Test
    public void testCharityAdminLoginSuccess() {
        inputValuesAndSubmit("charityadmin", "open");

        WebDriverWait wait = getDefaultWait();
        wait.until(new ExpectedCondition<Boolean>(){

            @Override
            public Boolean apply(WebDriver d) {
                WebElement h2 = driver.findElement(By.tagName("h2"));
                return "Charity Administration Panel".equals(h2.getText());
            }
        });

        // confirm that first tab is initially active: class value of active tab is set to 'active' by JavaScript
        WebElement tab1 = driver.findElement(By.id("tab_1"));
        // assertEquals(expected, actual);
        assertEquals(tab1.getAttribute("className"), "active");
    }

    private void inputValuesAndSubmit(String userName, String password) {
        WebElement userNameInput = driver.findElement(By.id("txtUsername"));
        WebElement passwordInput = driver.findElement(By.id("txtPassword"));

        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);

        WebElement submitButton = driver.findElement(By.id("button1"));
        submitButton.click();
    }
}
