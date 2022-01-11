package pages.basket.basketSummary;

import basketStore.Basket;
import handlers.FormatTextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketPage extends BasePage {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-item")
    private List<WebElement> orderLinesList;

    @FindBy(css = ".card-block:nth-of-type(2) .value")
    private WebElement totalPriceTaxIncl;

    @FindBy(css = ".js-subtotal")
    private WebElement totalQuantity;

    @FindBy(css = ".cart-summary>div>div>a")
    private WebElement proceedToCheckOutButton;

    public BasketPage clickOnProceedToCheckOut() {
        click(proceedToCheckOutButton);
        return this;
    }

    public Integer getTotalQuantityAsInteger() {
        return FormatTextHandler.getIntFromString(getText(totalQuantity));
    }

    public Integer getOrderLineQuantity(int orderLineIndex){
       return getListOfOrderLines().get(orderLineIndex).getProductQuantityAsInt();
    }

    public void setOrderLineQuantity(String quantity, int orderLineIndex, Basket basket) {
        int lineQuantity = basket.getBasketLists().get(orderLineIndex).getQuantity();
        int previousQuantity = basket.getBasketTotalQuantity();
        int difference = Integer.parseInt(quantity) - lineQuantity;
        getListOfOrderLines().get(orderLineIndex).setProductQuantityTo(quantity, basket.getBasketLists().get(orderLineIndex));
        waitUntilQuantityIsUpdated(previousQuantity, difference);
    }

    public void increaseOrderLineQuantityByClick(Basket basket, int orderLineIndex) {
        int previousQuantity = basket.getBasketTotalQuantity();
        getListOfOrderLines().get(orderLineIndex).clickOnIncreaseQuantityArrow(basket.getBasketLists().get(orderLineIndex));
        waitUntilQuantityIsUpdated(previousQuantity, 1);
    }

    public void decreaseOrderLineQuantityByClick(Basket basket, int orderLineIndex) {
        int previousQuantity = basket.getBasketTotalQuantity();
        getListOfOrderLines().get(orderLineIndex).clickOnDecreaseQuantityArrow(basket.getBasketLists().get(orderLineIndex));
        waitUntilQuantityIsUpdated(previousQuantity, -1);
    }

    public void clickOnTrashIcon(Basket basket, int orderLineIndex) {
        int lineQuantity = basket.getBasketLists().get(orderLineIndex).getQuantity();
        int previousQuantity = basket.getBasketTotalQuantity();
        getListOfOrderLines().get(orderLineIndex).clickOnTrashIcon(basket, orderLineIndex);
        waitUntilQuantityIsUpdated(previousQuantity, -lineQuantity);
    }

    public List<OrderLinePage> getListOfOrderLines() {
        List<OrderLinePage> list = new ArrayList<>();
        for (WebElement orderLine : orderLinesList) {
            list.add(new OrderLinePage(orderLine, driver));
        }
        return list;
    }

    public Basket getBasket() {
        Basket basket = new Basket();
        for (OrderLinePage orderLine : getListOfOrderLines()) {
            basket.addOrderLineToBasket(orderLine.toOrderLine());
        }
        return basket;
    }

    public String getTotalPriceTaxIncl() {
        return getText(totalPriceTaxIncl);
    }


    public BigDecimal getTotalPriceTaxInclAsBigDecimal() {
        return FormatTextHandler.getBigDecimalFromString(getTotalPriceTaxIncl());
    }

    public void waitUntilQuantityIsUpdated(int previousQuantity, int increase) {
        webDriverwait.until(x -> getTotalQuantityAsInteger().equals(previousQuantity + increase));
    }

}
