

import com.google.gson.Gson;
import hibernateEntities.FormFields;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FormServletApiTest {
    @Test
    public void test() {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:8080/CharityWare/FormServlet?req=structure&q=2");

        //The page source should be the returned JSON string
        String rawJson = driver.getPageSource();

        assertTrue(rawJson.contains("Age"));
        assertTrue(rawJson.contains("Ethnicity"));

        assertFalse(rawJson.contains("Hometown"));
    }

}