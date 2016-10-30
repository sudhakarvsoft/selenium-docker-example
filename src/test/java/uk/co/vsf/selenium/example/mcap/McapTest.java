package uk.co.vsf.selenium.example.mcap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.vsf.selenium.example.Browser;
import uk.co.vsf.selenium.example.Browser.Type;
import uk.co.vsf.selenium.example.page.IndexPage;
import uk.co.vsf.selenium.example.page.McapPage;

public class McapTest {

    private static Browser BROWSER;

    private final IndexPage indexPage = new IndexPage(BROWSER);

    @BeforeClass
    public static void beforeClass() {
        BROWSER = Browser.local(Type.FIREFOX);
    }

    @Before
    public void before() {
        BROWSER.navigateTo(indexPage);
    }

    @Test
    public void billionMcap_1() {
        final McapPage mcapPage = indexPage.getNavigation().navigateToMcapPage();
        assertTrue(mcapPage.isMcapPage());

        mcapPage.enterSharesOutstanding(new BigInteger("4600000000"))
                .enterCcurrentSharePrice(new BigDecimal("30.00"))
                .andCalculate();

        assertEquals("1,380,000,000", mcapPage.getMcap());
    }

    @Test
    public void billionMcap_2() {
        final McapPage mcapPage = indexPage.getNavigation().navigateToMcapPage();
        assertTrue(mcapPage.isMcapPage());

        mcapPage.enterSharesOutstanding(new BigInteger("2650000000"))
                .enterCcurrentSharePrice(new BigDecimal("1.89"))
                .andCalculate();

        assertEquals("50,085,000", mcapPage.getMcap());
    }

    @Test
    public void billionMcap_3() {
        final McapPage mcapPage = indexPage.getNavigation().navigateToMcapPage();
        assertTrue(mcapPage.isMcapPage());

        mcapPage.enterSharesOutstanding(new BigInteger("1220000000"))
                .enterCcurrentSharePrice(new BigDecimal("1.22"))
                .andCalculate();

        assertEquals("14,884,000", mcapPage.getMcap());
    }

    @Test
    public void billionMcap_4() {
        final McapPage mcapPage = indexPage.getNavigation().navigateToMcapPage();
        assertTrue(mcapPage.isMcapPage());

        mcapPage.enterSharesOutstanding(new BigInteger("3868000000"))
                .enterCcurrentSharePrice(new BigDecimal("54.2"))
                .andCalculate();

        assertEquals("2,096,456,000", mcapPage.getMcap());
    }

    @Test
    public void billionMcap_5() {
        final McapPage mcapPage = indexPage.getNavigation().navigateToMcapPage();
        assertTrue(mcapPage.isMcapPage());

        mcapPage.enterSharesOutstanding(new BigInteger("20100000000"))
                .enterCcurrentSharePrice(new BigDecimal("169.69"))
                .andCalculate();

        assertEquals("34,107,690,000", mcapPage.getMcap());
    }

    @AfterClass
    public static void afterClass() {
        BROWSER.close();
    }
}
