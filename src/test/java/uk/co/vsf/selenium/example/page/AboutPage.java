package uk.co.vsf.selenium.example.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import uk.co.vsf.selenium.example.Browser;

public class AboutPage extends BasePage {

    @FindBy(how = How.CLASS_NAME, using = "content")
    private List<WebElement> content;

    public AboutPage(final Browser browser) {
        super(browser);
    }

    public boolean isContentDisplayed() {
        return content.size() == 1;
    }
}
