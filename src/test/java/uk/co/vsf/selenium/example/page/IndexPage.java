package uk.co.vsf.selenium.example.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import uk.co.vsf.selenium.example.Browser;

public class IndexPage extends BasePage implements Page {

    private static final String location = "http://www.v-s-f.co.uk/trading-tools";

    @FindBy(how = How.CLASS_NAME, using = "content")
    private List<WebElement> content;

    public IndexPage(final Browser browser) {
        super(browser);
    }

    public boolean isContentDisplayed() {
        return content.size() == 1;
    }

    public String getLocation() {
        return location;
    }
}
