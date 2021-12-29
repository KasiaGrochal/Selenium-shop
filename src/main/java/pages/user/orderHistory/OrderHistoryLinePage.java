package pages.user.orderHistory;

import basketStore.Basket;
import handlers.DateHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public class OrderHistoryLinePage extends BasePage {

    Logger logger = LoggerFactory.getLogger(OrderHistoryLinePage.class);

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
        return getTextFromObject(referenceNumber);
    }

    public String getOrderDate() {
        return getTextFromObject(orderDate);
    }

    public String getOrderTotalPrice() {
        return getTextFromObject(orderTotalPrice);
    }

    public String getOrderPaymentType() {
        return getTextFromObject(orderPaymentType);
    }

    public String getOrderStatus() {
        return getTextFromObject(orderStatus);
    }

    public boolean checkIfOrderHistoryLineIsCorrect(Basket basket, String chosenPaymentMethod) {
        return (isOrderDateCorrect() &&
                isTotalPriceCorrect(basket) &&
                isPaymentTypeAsChosen(chosenPaymentMethod) &&
                isStatusAwaitingBankWire());
    }

    public boolean isOrderDateCorrect() {
        logger.info("Order Date is displayed Correctly: {}", getOrderDate().equals(DateHandler.getCurrentDateInMMddYYYY()));
        return getOrderDate().equals(DateHandler.getCurrentDateInMMddYYYY());
    }

    public boolean isTotalPriceCorrect(Basket basket) {
        logger.info("Order Total Price is displayed Correctly: {}", getOrderTotalPrice().equals(basket.getBasketTotalCost()));
        return getOrderTotalPrice().equals(basket.getBasketTotalCost());
    }

    public boolean isPaymentTypeAsChosen(String chosenPayment) {
        logger.info("Payment Method is bank transfer: {}", getOrderPaymentType().equals(chosenPayment));
        return getOrderPaymentType().equals(chosenPayment);
    }

    public boolean isStatusAwaitingBankWire() {
        logger.info("Status is Awaiting Bank Wire: {}", getOrderStatus().contains("Awaiting bank wire payment"));
        return getOrderStatus().contains("Awaiting bank wire payment");
    }

    public OrderHistoryLinePage clickOnDetailsButton() {
        clickOnButton(orderDetails);
        return this;
    }


}
