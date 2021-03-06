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
        return this;
    }

    public OrderLinePage clickOnIncreaseQuantityArrow(OrderLine orderLine){
        noStaleClick(increaseProductQuantityArrow);
        orderLine.setQuantity(orderLine.getQuantity()+1);
        orderLine.updateTotalCost();
        return this;
    }



    public OrderLinePage clickOnDecreaseQuantityArrow(OrderLine orderLine){
        noStaleClick(decreaseProductQuantityArrow);
        orderLine.setQuantity(orderLine.getQuantity()-1);
        orderLine.updateTotalCost();
        return this;
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public BigDecimal getProductPriceAsBigDecimal() {
        return FormatTextHandler.getBigDecimalFromString(getProductPrice());
    }

    public String getProductQuantity() {
        return productQuantity.getAttribute("value");
    }

    public OrderLinePage setProductQuantityTo(String value, OrderLine orderLine){
        action.sendKeys(productQuantity, Keys.BACK_SPACE).build().perform();
        send(productQuantity,value);
        action.sendKeys(productQuantity, Keys.ENTER).build().perform();
        orderLine.setQuantity(Integer.parseInt(value));
        orderLine.updateTotalCost();
        return this;
    }

    public Integer getProductQuantityAsInt() {
        return FormatTextHandler.getIntFromString(getProductQuantity());
    }

    public OrderLine toOrderLine() {
        Product product = new Product(getProductName(), getProductPriceAsBigDecimal());
        return new OrderLine(product, getProductQuantityAsInt());
    }


}
