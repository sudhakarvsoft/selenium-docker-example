package uk.co.vsf.selenium.example.page.fragement;

import org.openqa.selenium.support.PageFactory;

import uk.co.vsf.selenium.example.Browser;

/**
 * A base for all page fragments. A page is an extension of a fragment.
 *
 * @author Victoria
 */
public abstract class BasePageFragment {

    private final Browser browser;

    public BasePageFragment(final Browser browser) {
        this.browser = browser;
        PageFactory.initElements(browser.getDriver(), this);
    }

    protected Browser getBrowser() {
        return browser;
    }
}
