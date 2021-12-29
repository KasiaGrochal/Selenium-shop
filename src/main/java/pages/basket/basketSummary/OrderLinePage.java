package pages.basket.basketSummary;

import basketStore.Basket;
import basketStore.OrderLine;
import basketStore.Product;
import handlers.FormatTextHandler;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pages.BasePage;

import java.math.BigDecimal;

public class OrderLinePage extends BasePage {

    public OrderLinePage(WebElement orderLine, WebDriver driver) {
        super(driver);
        DefaultElementLocatorFactory parent = new DefaultElementLocatorFactory(orderLine);
        PageFactory.initElements(parent, this);
    }

    @FindBy(css = ".product-line-info>a")
    private WebElement productName;

    @FindBy(css = ".current-price")
    private WebElement productPrice;

    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement productQuantity;

    @FindBy (css = ".js-increase-product-quantity")
    private WebElement increaseProductQuantityArrow;

    @FindBy (css = ".js-decrease-product-quantity")
    private WebElement decreaseProductQuantityArrow;

    @FindBy (css = ".remove-from-cart")
    private WebElement trashIcon;



    public OrderLinePage clickOnTrashIcon(Basket basket, int orderLineIndex){
        noStaleClick(trashIcon);
        basket.deleteOrderLine(orderLineIndex);
        basket.updateBasketTotalCost();
        return this;
    }

    public OrderLinePage clickOnIncreaseQuantityArrow(OrderLine orderLine, Basket basket){
        noStaleClick(increaseProductQuantityArrow);
        orderLine.setQuantity(orderLine.getQuantity()+1);
        orderLine.updateTotalCost();
        basket.updateBasketTotalCost();
        return this;
    }



    public OrderLinePage clickOnDecreaseQuantityArrow(OrderLine orderLine, Basket basket){
        noStaleClick(decreaseProductQuantityArrow);
        orderLine.setQuantity(orderLine.getQuantity()-1);
        orderLine.updateTotalCost();
        basket.updateBasketTotalCost();
        return this;
    }

    public String getProductName() {
        return getTextFromObject(productName);
    }

    public String getProductPrice() {
        return getTextFromObject(productPrice);
    }

    public BigDecimal getProductPriceAsBigDecimal() {
        return FormatTextHandler.getBigDecimalFromString(getProductPrice());
    }

    public String getProductQuantity() {
        return productQuantity.getAttribute("value");
    }

    public OrderLinePage setProductQuantityTo(String value, OrderLine orderLine, Basket basket){
        action.sendKeys(productQuantity, Keys.BACK_SPACE).build().perform();
        sendKeysToObject(productQuantity,value);
        action.sendKeys(productQuantity, Keys.ENTER).build().perform();
        orderLine.setQuantity(Integer.parseInt(value));
        orderLine.updateTotalCost();
        basket.updateBasketTotalCost();
        return this;
    }

    public Integer getProductQuantityAsInt() {
        return FormatTextHandler.getIntFromString(getProductQuantity());
    }

    public OrderLine createOrderLine() {
        Product product = new Product(getProductName(), getProductPriceAsBigDecimal());
        return new OrderLine(product, getProductQuantityAsInt());
    }


}
