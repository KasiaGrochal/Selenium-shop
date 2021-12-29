package pages.basket.checkOut;

import basketStore.Basket;
import handlers.FormatTextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

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


    public List<OrderConfirmationLinePage> getListOfOrderConfirmationLines() {
        List<OrderConfirmationLinePage> list = new ArrayList<>();
        for (WebElement orderLine : listOfOrderLines) {
            list.add(new OrderConfirmationLinePage(orderLine, driver));
        }
        return list;
    }

    public Basket getBasket() {
        Basket basket = new Basket();
        for (OrderConfirmationLinePage orderLine : getListOfOrderConfirmationLines()) {
            basket.addOrderLineToBasket(orderLine.createOrderLine());
        }
        return basket;
    }

    public String getOrderReferenceNumber() {
        return getTextFromObject(orderReferenceNumber);
    }

    public String getOrderPaymentMethod() {
        return getTextFromObject(orderPaymentMethod);
    }

    public String getOrderDeliveryMethod() {
        return getTextFromObject(orderDeliveryMethod);
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
