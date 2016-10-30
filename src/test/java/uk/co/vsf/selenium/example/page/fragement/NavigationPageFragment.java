package uk.co.vsf.selenium.example.page.fragement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import uk.co.vsf.selenium.example.Browser;
import uk.co.vsf.selenium.example.page.AboutPage;
import uk.co.vsf.selenium.example.page.IndexPage;
import uk.co.vsf.selenium.example.page.McapPage;

public class NavigationPageFragment extends BasePageFragment {

    @FindBy(how = How.LINK_TEXT, using = "Home")
    private WebElement homeLink;

    @FindBy(how = How.LINK_TEXT, using = "About")
    private WebElement aboutLink;

    @FindBy(how = How.LINK_TEXT, using = "Dividend Re-investment Calculator")
    private WebElement dividendLink;

    @FindBy(how = How.LINK_TEXT, using = "Mcap Calculator")
    private WebElement mcapLink;

    public NavigationPageFragment(final Browser browser) {
        super(browser);
    }

    public IndexPage navigateToIndexPage() {
        homeLink.click();
        return new IndexPage(getBrowser());
    }

    public AboutPage navigateToAboutPage() {
        aboutLink.click();
        return new AboutPage(getBrowser());
    }

    public McapPage navigateToMcapPage() {
        mcapLink.click();
        return new McapPage(getBrowser());
    }
}
