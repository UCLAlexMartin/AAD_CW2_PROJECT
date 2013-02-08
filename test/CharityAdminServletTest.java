import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test class for CharityAdminServlet
 */
public class CharityAdminServletTest extends CharityWareBaseTestCase {
    private static final String TARGET_PAGE = "CharityAdminServlet";

    @Before
    public void openLoginJsp() {
        openInternalPage(TARGET_PAGE);
    }

    @Test
    public void testAddRowAndRemove() throws InterruptedException {
        confirmFormWizardAppeared();
        addRow();

        WebElement typeElement = driver.findElement(By.id("type_0"));

        // value 1 means "Text (alphanumeric)
        assertEquals(typeElement.getAttribute("value"), "1", "Default type should be alphanumeric text.");

        WebElement removeRowButton = findChildByText(driver.findElement(By.id("rowsetrows")), "button", "Remove row");
        removeRowButton.click();

        WebDriverWait wait = getDefaultWait();
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                WebElement element = webDriver.findElement(By.id("rowsetrows"));
                try {
                    element.findElement(By.tagName("div"));
                    return false;
                } catch (NoSuchElementException e) {
                    return true;
                }
            }
        });
    }

    @Test
    public void testAddRowAndSubmit() throws InterruptedException {
        WebElement formList = driver.findElement(By.id("myformslist"));
        final int numberOfForms = formList.findElements(By.tagName("option")).size();

        confirmFormWizardAppeared();

        addRow();

        WebElement formName = driver.findElement(By.id("formname"));
        formName.sendKeys("test");

        WebElement submitButton = driver.findElement(By.id("btnSubmitForm"));
        submitButton.click();

        WebDriverWait wait = getDefaultWait();
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                WebElement formList = driver.findElement(By.id("myformslist"));
                // check if new form is added
                return formList.findElements(By.tagName("option")).size() != numberOfForms;
            }
        });
        

        formList = driver.findElement(By.id("myformslist"));
        List<WebElement> options = formList.findElements(By.tagName("option"));
        options.get(options.size() - 1).click();
        WebElement viewStructureButton =
                findChildByText(driver.findElement(By.id("myforms")), "button", "View structure");
        viewStructureButton.click();

        wait = getDefaultWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("gridtable")));
        WebElement structureTable = driver.findElement(By.className("gridtable"));
        WebElement td = structureTable.findElement(By.tagName("td"));
        assertEquals(td.getText(), "Name");

    }

    private void addRow() {
        WebElement fieldNameInput = driver.findElement(By.id("fieldname"));
        fieldNameInput.sendKeys("Name");

        WebElement mandatoryCheck = driver.findElement(By.id("rowrequired"));
        mandatoryCheck.click();

        WebElement userNameInput = driver.findElement(By.id("formwizard"));
        WebElement addRowButton = findChildByText(userNameInput, "button", "Add row");
        assertNotNull(addRowButton);
        addRowButton.click();

        waitForElementWithIdAppeared("type_0_name");
    }

    private void confirmFormWizardAppeared() {
        WebElement buttonParent = driver.findElement(By.id("myforms"));
        WebElement addNewForm = findChildByText(buttonParent, "button", "Add new Form");
        assertNotNull(addNewForm);
        addNewForm.click();

        waitForElementWithIdAppeared("formwizard");
    }

    private WebElement findChildByText(WebElement element, String tagName, String searchText) {
        List<WebElement> children = element.findElements(By.tagName(tagName));
        for (WebElement child: children) {
            if (searchText.equals(child.getText())) {
                return child;
            }
        }
        return  null;
    }

    private void waitForElementWithIdAppeared(final String id) {
        WebDriverWait wait = getDefaultWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }
}
