package pages.user.orderHistory.details;

import basketStore.OrderLine;
import basketStore.Product;
import handlers.FormatTextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pages.BasePage;

import java.math.BigDecimal;

public class DetailsProductLine extends BasePage {
    public DetailsProductLine(WebElement detailsProductLine, WebDriver driver) {
        super(driver);
        DefaultElementLocatorFactory parent = new DefaultElementLocatorFactory(detailsProductLine);
        PageFactory.initElements(parent, this);
    }

    @FindBy(css = "td>strong>a")
    private WebElement productName;

    @FindBy(css = "td:nth-of-type(2)")
    private WebElement productQuantity;

    @FindBy(css = "td:nth-of-type(3)")
    private WebElement productUnitPrice;

    @FindBy(css = "td:nth-of-type(4)")
    private WebElement productTotalPrice;

    public String getProductTotalPrice(){
        return getText(productTotalPrice);
    }

    public BigDecimal getProductTotalPriceAsBigDecimal(){
        return FormatTextHandler.getBigDecimalFromString(getProductTotalPrice());
    }

    public String getProductName(){
        return getText(productName);
    }

    public String getProductQuantity(){
        return getText(productQuantity);
    }

    public Integer getProductQuantityAsInt(){
        return FormatTextHandler.getIntFromString(getProductQuantity());
    }

    public String getProductUnitPrice(){
        return getText(productUnitPrice);
    }

    public BigDecimal getProductUnitPriceAsBigDecimal(){
        return FormatTextHandler.getBigDecimalFromString(getProductUnitPrice());
    }

    public OrderLine toOrderLine() {
        Product product = new Product(getTrimmedProductName(), getProductUnitPriceAsBigDecimal());
        OrderLine orderLine = new OrderLine(product,getProductQuantityAsInt());
        orderLine.setTotalCost(getProductTotalPriceAsBigDecimal());
        return orderLine;
    }

    public String getTrimmedProductName(){
        return FormatTextHandler.trimProductName(getProductName());
    }
}
