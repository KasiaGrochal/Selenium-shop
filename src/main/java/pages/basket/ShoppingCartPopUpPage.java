package pages.basket;

import basketStore.OrderLine;
import basketStore.Product;
import handlers.FormatTextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.commons.products.ProductDetailsPage;

import java.math.BigDecimal;

public class ShoppingCartPopUpPage extends BasePage {
    public ShoppingCartPopUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = ".product-price:nth-of-type(1)")
    private WebElement productPrice;

    @FindBy(css = ".product-quantity>strong")
    private WebElement productQuantity;

    @FindBy(css = ".product-total .value")
    private WebElement totalValue;

    @FindBy(css = ".btn-secondary")
    private WebElement continueShoppingButton;

    @FindBy(css = ".cart-content-btn>a")
    private WebElement proceedToCheckOutButton;

    @FindBy(css = ".cart-products-count:nth-of-type(1)")
    private WebElement thereIsXItems;

    @FindBy(css = "#blockcart-modal")
    private WebElement popUpDialog;

    public String getProductQuantity(){
        waitForElementToBeVisibleFluent(productQuantity);
       return getText(productQuantity);
    }

    public Integer getProductQuantityAsInteger(){
        return FormatTextHandler.getIntFromString(getProductQuantity());
    }

    public ShoppingCartPopUpPage clickOnProceedToCheckOut() {
        waitForAttributeToBe(popUpDialog,"style","display: block;");
        click(proceedToCheckOutButton);
        return this;
    }

    public String getThereIsXItemsInfo() {
        waitForAttributeToBe(popUpDialog,"style","display: block;");
        waitForElementToBeVisibleFluent(thereIsXItems);
        return getText(thereIsXItems);
    }

    public Integer getThereIsXItemsInfoAsInt() {
        return FormatTextHandler.getIntFromString(getThereIsXItemsInfo());
    }

    public String getTotalProductsValue() {
        waitForElementToBeVisibleFluent(totalValue);
        return getText(totalValue);
    }

    public BigDecimal getTotalProductsValueAsBigDecimal() {
        return FormatTextHandler.getBigDecimalFromString(getTotalProductsValue());
    }

    public String getProductName() {
        waitForElementToBeVisibleFluent(productName);
        return getText(productName);
    }

    public ProductDetailsPage clickOnContinueShoppingButton() {
        waitForAttributeToBe(popUpDialog,"style","display: block;");
        click(continueShoppingButton);
        waitForAttributeToBe(popUpDialog,"style","display: none;");
        return new ProductDetailsPage(driver);
    }

    public String getProductPrice() {
        waitForElementToBeVisibleFluent(productPrice);
        return getText(productPrice);
    }

    public BigDecimal getProductPriceAsBigDecimal() {
        return FormatTextHandler.getBigDecimalFromString(getProductPrice());
    }

    public OrderLine toOrderLine() {
        Product product = new Product(getProductName(), getProductPriceAsBigDecimal());
        return new OrderLine(product, getProductQuantityAsInteger());
    }

}
