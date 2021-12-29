package pages.commons.products;

import handlers.CalculatorHelper;
import handlers.FormatTextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pages.BasePage;

import java.math.BigDecimal;

public class ProductBoxPage extends BasePage {

    public ProductBoxPage(WebElement product, WebDriver driver) {
        super(driver);
        DefaultElementLocatorFactory parent = new DefaultElementLocatorFactory(product);
        PageFactory.initElements(parent, this);
    }

    @FindBy(css = ".product-title")
    private WebElement productName;

    @FindBy(css = ".product-flag")
    private WebElement discountFlag;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = "[aria-label='Price']")
    private WebElement discountPrice;

    public String getProductName() {
        return getTextFromObject(productName);
    }

    public String getDiscountFlagValue(){
        return getTextFromObject(discountFlag);
    }

    public String getRegularPrice(){
        return getTextFromObject(regularPrice);
    }
    private BigDecimal getRegularPriceAsBigDecimal(){
        return FormatTextHandler.getBigDecimalFromString(getRegularPrice());
    }

    public String getDiscountPrice(){
        return getTextFromObject(discountPrice);
    }

    private BigDecimal getDiscountPriceAsBigDecimal(){
        return FormatTextHandler.getBigDecimalFromString(getDiscountPrice());
    }

    public boolean isRegularPriceVisible(){
        return regularPrice.isDisplayed();
    }

    public boolean isDiscountPriceVisible(){
        return discountPrice.isDisplayed();
    }

    public boolean isDiscountCalculatedCorrectly(){
        return CalculatorHelper.isDiscountCalculatedCorrectly(getRegularPriceAsBigDecimal(),getDiscountPriceAsBigDecimal());
    }

}
