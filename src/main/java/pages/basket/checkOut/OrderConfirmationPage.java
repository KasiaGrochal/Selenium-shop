package pages.basket.checkOut;

import basketStore.Basket;
import handlers.FormatTextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderConfirmationPage extends BasePage {

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".order-confirmation-table>div")
    private List<WebElement> listOfOrderLines;

    @FindBy(css = "#order-details>ul>li")
    private WebElement orderReferenceNumber;

    @FindBy(css = "#order-details>ul>li:nth-of-type(2)")
    private WebElement orderPaymentMethod;

    @FindBy(css = "#order-details>ul>li:nth-of-type(3)")
    private WebElement orderDeliveryMethod;

    @FindBy(css = ".total-value>td:nth-of-type(2)")
    private WebElement totalOrderCost;

    public String getTotalOrderCost(){
        return getText(totalOrderCost);
    }

    public BigDecimal getTotalOrderCostAsBigDecimal(){
        return FormatTextHandler.getBigDecimalFromString(getTotalOrderCost());
    }


    public List<OrderConfirmationLinePage> getListOfOrderConfirmationLines() {
        List<OrderConfirmationLinePage> list = new ArrayList<>();
        listOfOrderLines.forEach(x->list.add(new OrderConfirmationLinePage(x, driver)));
        return list;
    }

    public Basket getBasket() {
        Basket basket = new Basket();
        getListOfOrderConfirmationLines().forEach(x->basket.addOrderLineToBasket(x.toOrderLine()));
        return basket;
    }

    public String getOrderReferenceNumber() {
        return getText(orderReferenceNumber);
    }

    public String getOrderPaymentMethod() {
        return getText(orderPaymentMethod);
    }

    public String getOrderDeliveryMethod() {
        return getText(orderDeliveryMethod);
    }
    public String getTrimmedPaymentMethod(){
        return FormatTextHandler.trimText(getOrderPaymentMethod());
    }

    public String getTrimmedDeliveryMethod(){
        return FormatTextHandler.trimText(getOrderDeliveryMethod());
    }

    public String getTrimmedReferenceNumber(){
        return FormatTextHandler.trimText(getOrderReferenceNumber());
    }
}
