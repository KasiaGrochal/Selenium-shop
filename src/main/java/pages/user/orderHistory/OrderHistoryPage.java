package pages.user.orderHistory;

import basketStore.Basket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryPage extends BasePage {


    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".table>tbody>tr")
    List<WebElement> listOfOrders;

    public List<OrderHistoryLinePage> getListOfOrders() {
        List<OrderHistoryLinePage> list = new ArrayList<>();
        for (WebElement line : listOfOrders) {
            list.add(new OrderHistoryLinePage(line, driver));
        }
        return list;
    }

    public boolean checkIfOrderIsOnTheList(String referenceNumber) {
        for (OrderHistoryLinePage line : getListOfOrders()) {
            if (line.getReferenceNumber().equals(referenceNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfOrderLineIsCorrect(String referenceNumber, Basket basket, String paymentMethod) {
        for (OrderHistoryLinePage line : getListOfOrders()) {
            if (line.getReferenceNumber().equals(referenceNumber)) {
                return line.checkIfOrderHistoryLineIsCorrect(basket,paymentMethod);
            }
        }
        return false;
    }

    public OrderHistoryPage clickOnDetails(String referenceNumber){
        for (OrderHistoryLinePage line : getListOfOrders()) {
            if (line.getReferenceNumber().equals(referenceNumber)) {
                line.clickOnDetailsButton();
                break;
            }
    }
        return this;
    }
}
