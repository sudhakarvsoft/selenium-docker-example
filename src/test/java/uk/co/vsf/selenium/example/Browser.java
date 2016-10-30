package uk.co.vsf.selenium.example;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import uk.co.vsf.selenium.example.page.Page;

public class Browser {

    public enum Type {
        FIREFOX,
        CHROME;
    }

    private WebDriver driver = null;
    private final boolean local;
    private final Type type;

    /**
     * Get a remote Browser instance of the specified type.
     *
     * @param type
     *            of browser.
     * @return remote browser instance.
     */
    public static Browser instance() {
        final boolean local = Boolean.valueOf(System.getProperty("browser.driver.local"));
        final Type type = Type.valueOf(System.getProperty("browser.driver.type"));
        return new Browser(local, type);
    }

    /**
     * Get a remote Browser instance of the specified type.
     *
     * @param type
     *            of browser.
     * @return remote browser instance.
     */
    public static Browser remote(final Type type) {
        return new Browser(false, type);
    }

    /**
     * Get a local Browser instance of the specified type.
     *
     * @param type
     *            of browser.
     * @return local browser instance.
     */
    public static Browser local(final Type type) {
        return new Browser(true, type);
    }

    private Browser(final boolean local, final Type type) {
        this.local = local;
        this.type = type;
    }

    /**
     * Gets an instance of the browser.
     *
     * @return remote or local browser driver depending on what was requested.
     */
    public WebDriver getDriver() {
        if (driver == null) {
            attachBrowser();
        }

        return driver;
    }

    private void attachBrowser() {
        if (local) {
            localBrowser();
        } else {
            remoteBrowser();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void localBrowser() {
        if (Type.CHROME.equals(type)) {
            throw new NotImplementedException();

        } else if (Type.FIREFOX.equals(type)) {
            final File path = new File(System.getProperty("browser.driver.local.firefox.location"));
            System.setProperty("webdriver.gecko.driver", path.getAbsolutePath());
            driver = new FirefoxDriver();
        }
    }

    private void remoteBrowser() {
        Capabilities capabilities = null;
        switch (type) {
            case FIREFOX:
                final Map<String, String> caps = new HashMap<String, String>();
                caps.put("browserName", "firefox");
                capabilities = new DesiredCapabilities(caps);
                break;
            case CHROME:
                capabilities = DesiredCapabilities.chrome();
                break;
        }

        URL url = null;
        try {
            url = new URL(System.getProperty("browser.driver.remote.url"));
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver = new RemoteWebDriver(url, capabilities);
    }

    /**
     * Navigate to a specific page.
     *
     * @param page
     *            to navigate to
     */
    public void navigateTo(final Page page) {
        final Navigation navigation = driver.navigate();
        navigation.to(page.getLocation());
    }

    public void close() {
        this.driver.quit();
    }
}
