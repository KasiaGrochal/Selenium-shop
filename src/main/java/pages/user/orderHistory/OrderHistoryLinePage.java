package pages.user.orderHistory;

import handlers.FormatTextHandler;
import orderSummary.OrderSummary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pages.BasePage;

import java.math.BigDecimal;

public class OrderHistoryLinePage extends BasePage {

    public OrderHistoryLinePage(WebElement orderHistoryLine, WebDriver driver) {
        super(driver);
        DefaultElementLocatorFactory parent = new DefaultElementLocatorFactory(orderHistoryLine);
        PageFactory.initElements(parent, this);
    }

    @FindBy(css = "th")
    private WebElement referenceNumber;

    @FindBy(css = "td:nth-of-type(1)")
    private WebElement orderDate;

    @FindBy(css = "td:nth-of-type(2)")
    private WebElement orderTotalPrice;

    @FindBy(css = "td:nth-of-type(3)")
    private WebElement orderPaymentType;

    @FindBy(css = "td:nth-of-type(4)>span")
    private WebElement orderStatus;

    @FindBy(css = "td:nth-of-type(6)>a:nth-of-type(1)")
    private WebElement orderDetails;

    public String getReferenceNumber() {
        waitForElementToBeVisibleFluent(referenceNumber);
        return getText(referenceNumber);
    }

    public String getOrderDate() {
        return getText(orderDate);
    }

    public String getOrderTotalPrice() {
        return getText(orderTotalPrice);
    }

    public BigDecimal getOrderTotalPriceAsBigDecimal(){
        return FormatTextHandler.getBigDecimalFromString(getOrderTotalPrice());
    }

    public String getOrderPaymentType() {
        return getText(orderPaymentType);
    }

    public String getOrderStatus() {
        return getText(orderStatus);
    }

    public OrderSummary toOrderSummary(){
        return new OrderSummary(getOrderDate(),getOrderTotalPriceAsBigDecimal(),getOrderPaymentType(),getOrderStatus());
    }

    public OrderHistoryLinePage clickOnDetailsButton() {
        click(orderDetails);
        return this;
    }


}
