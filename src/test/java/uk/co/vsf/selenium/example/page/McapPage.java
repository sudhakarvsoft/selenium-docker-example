package uk.co.vsf.selenium.example.page;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import uk.co.vsf.selenium.example.Browser;

public class McapPage extends BasePage {

    private enum SharesMultiplier {
        BILLION("B", new BigDecimal("10000000000")),
        MILLION("M", new BigDecimal("1000000")),
        THOUSAND("K", new BigDecimal("1000"));

        private String multiplier;
        private BigDecimal divisor;

        private SharesMultiplier(final String multiplier, final BigDecimal divisor) {
            this.multiplier = multiplier;
            this.divisor = divisor;
        }

        public String getMultiplier() {
            return multiplier;
        }

        public BigDecimal getDivisor() {
            return divisor;
        }
    }

    @FindBy(how = How.ID, using = "SharesOutstanding")
    private WebElement sharesOutstandingInputBox;

    @FindBy(how = How.ID, using = "SharesOutstandingMultiplier")
    private WebElement sharesOutstandingMultiplierDropDownBox;

    @FindBy(how = How.ID, using = "CurrentSharePrice")
    private WebElement currentSharePriceInputBox;

    @FindBy(how = How.ID, using = "Calculate")
    private WebElement calculateButton;

    @FindBy(how = How.CLASS_NAME, using = "subscr")
    private List<WebElement> footNoteText;

    @FindBy(how = How.ID, using = "MCap")
    private WebElement mCapValue;

    public McapPage(final Browser browser) {
        super(browser);
    }

    public McapPage enterSharesOutstanding(final BigInteger sharesOutstanding) {
        if (sharesOutstanding == null) {
            throw new IllegalArgumentException("value must be more than 1000 shares");
        }

        setSharesOutstanding(sharesOutstanding);

        return this;
    }

    public McapPage enterSharesOutstanding(final BigInteger sharesOutstanding,
            final SharesMultiplier sharesMultiplier) {
        if ((sharesOutstanding == null) || (sharesMultiplier == null)) {
            throw new IllegalArgumentException(
                    "Must provide a sharesOutstanding value of more than 1000 shares and provide a multiplier");
        }

        setSharesOutstanding(sharesOutstanding, sharesMultiplier);

        return this;
    }

    public void andCalculate() {
        this.calculateButton.click();
        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setSharesOutstanding(final BigInteger sharesOutstanding) {
        final BigDecimal sharesOutstandingDecimal = new BigDecimal(sharesOutstanding);
        sharesOutstandingInputBox.sendKeys("" + sharesOutstandingDecimal);
    }

    private void setSharesOutstanding(final BigInteger sharesOutstanding, final SharesMultiplier sharesMultiplier) {
        final BigDecimal sharesOutstandingDecimal = new BigDecimal(sharesOutstanding, 5);
        sharesOutstandingInputBox
                .sendKeys("" + sharesOutstandingDecimal.divide(sharesMultiplier.getDivisor(), RoundingMode.UP));
        enterSharesOutstandingMultiplier(sharesMultiplier);
    }

    private void enterSharesOutstandingMultiplier(final SharesMultiplier sharesMultiplier) {
        sharesOutstandingMultiplierDropDownBox.sendKeys(sharesMultiplier.getMultiplier());
    }

    public McapPage enterCcurrentSharePrice(final BigDecimal sharePriceInPence) {
        currentSharePriceInputBox.sendKeys("" + sharePriceInPence);
        return this;
    }

    public boolean isMcapPage() {
        return (footNoteText.size() > 1)
                && footNoteText.get(1).getText().contains("The calculations are not guaranteed to be exact");
    }

    public String getMcap() {
        return mCapValue.getText();
    }

}
