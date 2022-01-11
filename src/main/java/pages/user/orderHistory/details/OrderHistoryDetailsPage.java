package pages.user.orderHistory.details;

import basketStore.Basket;
import handlers.FormatTextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDetailsPage extends BasePage {
    public OrderHistoryDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-products>tbody>tr")
    private List <WebElement> orderHistoryProductLine;

    @FindBy(css = "#delivery-address > address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address > address")
    private WebElement invoiceAddress;

    @FindBy(css = "tfoot>tr:nth-of-type(3)>td:nth-of-type(2)")
    private WebElement totalOrderCost;

    public String getTotalOrderCost(){
        return getText(totalOrderCost);
    }

    public BigDecimal getTotalOrderCostAsBigDecimal(){
        return FormatTextHandler.getBigDecimalFromString(getTotalOrderCost());
    }

    public List<DetailsProductLine> getListOfProductLines() {
        List<DetailsProductLine> list = new ArrayList<>();
        for (WebElement productLine : orderHistoryProductLine) {
            list.add(new DetailsProductLine(productLine, driver));
        }
        return list;
    }

    public Basket getBasket() {
        Basket basket = new Basket();
        for (DetailsProductLine productLine : getListOfProductLines()) {
            basket.addOrderLineToBasket(productLine.toOrderLine());
        }
        return basket;
    }

    public String getDeliveryAddress() {
        return getText(deliveryAddress);
    }
    public String getInvoiceAddress() {
        return getText(invoiceAddress);
    }
}
