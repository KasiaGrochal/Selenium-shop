package pages.basket.checkOut;

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

public class OrderConfirmationLinePage extends BasePage {

    public OrderConfirmationLinePage(WebElement orderLine, WebDriver driver) {
        super(driver);
        DefaultElementLocatorFactory parent = new DefaultElementLocatorFactory(orderLine);
        PageFactory.initElements(parent, this);
    }

    @FindBy(css = "div:nth-of-type(2)>span")
    private WebElement productName;

    @FindBy(css = ".order-confirmation-table>div>div>div>div:nth-of-type(2)")
    private WebElement itemQuantity;

    @FindBy(css = ".order-confirmation-table>div>div>div>div:nth-of-type(1)")
    private WebElement singleItemPrice;

    @FindBy(css = ".order-confirmation-table>div>div>div>div:nth-of-type(3)")
    private WebElement itemTotalPrice;

    public String getProductName(){
        waitForElementToBeVisibleFluent(productName);
        return getText(productName);
    }

    public String getTrimmedProductName(){
        return FormatTextHandler.trimProductName(getProductName());
    }

    public String getSingleProductPrice(){
        waitForElementToBeVisibleFluent(productName);
        return getText(singleItemPrice);
    }

    public BigDecimal getSingleProductPriceAsBigDecimal(){
        return FormatTextHandler.getBigDecimalFromString(getSingleProductPrice());
    }

    public String getProductQuantity(){
        waitForElementToBeVisibleFluent(productName);
        return getText(itemQuantity);
    }

    public Integer getProductQuantityAsInt(){
        return FormatTextHandler.getIntFromString(getProductQuantity());
    }

    public String getProductTotalPrice(){
        waitForElementToBeVisibleFluent(productName);
        return getText(itemTotalPrice);
    }

    public BigDecimal getProductTotalPriceAsBigDecimal(){
        return FormatTextHandler.getBigDecimalFromString(getProductTotalPrice());
    }

    public OrderLine createOrderLine() {
        Product product = new Product(getTrimmedProductName(), getSingleProductPriceAsBigDecimal());
        OrderLine orderLine = new OrderLine(product,getProductQuantityAsInt());
        orderLine.setTotalCost(getProductTotalPriceAsBigDecimal());
        return orderLine;
    }




}
