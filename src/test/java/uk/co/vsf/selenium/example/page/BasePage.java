package uk.co.vsf.selenium.example.page;

import uk.co.vsf.selenium.example.Browser;
import uk.co.vsf.selenium.example.page.fragement.BasePageFragment;
import uk.co.vsf.selenium.example.page.fragement.NavigationPageFragment;

/**
 * A base for all pages. Contains a method for getting hold of the navigation
 * from any page.
 *
 * @author Victoria
 */
public abstract class BasePage extends BasePageFragment {

    public BasePage(final Browser browser) {
        super(browser);
    }

    public NavigationPageFragment getNavigation() {
        return new NavigationPageFragment(getBrowser());
    }
}
