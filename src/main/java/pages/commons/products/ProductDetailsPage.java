package pages.commons.products;

import basketStore.Basket;
import handlers.CalculatorHelper;
import handlers.FormatTextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.basket.ShoppingCartPopUpPage;
import pages.basket.basketSummary.BasketPage;
import pages.commons.TopMenuPage;

import java.math.BigDecimal;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".h1")
    private WebElement productName;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = ".discount-percentage")
    private WebElement discountPercentageInfo;

    @FindBy(css = ".regular-price")
    private WebElement regularPriceInfo;

    @FindBy(css = "[itemprop='price']")
    private WebElement discountPriceInfo;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartButton;


    public String getDiscountPercentageInfo() {
        return getTextFromObject(discountPercentageInfo);
    }

    public boolean isRegularPriceDisplayed() {
        return regularPriceInfo.isDisplayed();
    }

    public boolean isDiscountPriceDisplayed() {
        return discountPriceInfo.isDisplayed();
    }

    public String getRegularPrice() {
        return getTextFromObject(regularPriceInfo);
    }

    private BigDecimal getRegularPriceAsBigDecimal() {
        return FormatTextHandler.getBigDecimalFromString(getRegularPrice());
    }

    public String getDiscountPrice() {
        webDriverwait.until(x -> !getTextFromObject(discountPriceInfo).isEmpty());
        return getTextFromObject(discountPriceInfo);
    }

    public BigDecimal getDiscountPriceAsBigDecimal() {
        return FormatTextHandler.getBigDecimalFromString(getDiscountPrice());
    }

    public boolean isDiscountCalculatedCorrectly() {
        return CalculatorHelper.isDiscountCalculatedCorrectly(getRegularPriceAsBigDecimal(), getDiscountPriceAsBigDecimal());
    }

    public String getProductName() {
        return getTextFromObject(productName);
    }

    public ProductDetailsPage addSingleProductToBasket(Basket basket, int quantity) {
        while (!addToCartButton.isEnabled()){
            goToRandomProduct();
        }
        sendKeysToObject(quantityInput, String.valueOf(quantity));
        basket.addProductToBasket(getProductName(), getDiscountPriceAsBigDecimal(), quantity);
        noStaleClick(addToCartButton);
        return this;
    }

    //STEP:
    public void goToRandomProduct() {
        new TopMenuPage(driver).
                goToRandomCategory();
        new ProductsGridPage(driver).
                clickOnRandomProduct();
    }


    //STEP:
    public ProductDetailsPage addRandomProductsToBasket(Basket basket, int repeat, int quantity) {
        for (int i = 0; i < repeat; i++) {
            goToRandomProduct();
            addSingleProductToBasket(basket, quantity);
            new ShoppingCartPopUpPage(driver).
                    clickOnContinueShoppingButton();
        }
        return this;
    }

    //STEP:
    public ProductDetailsPage addRandomProductToBasketAndGoToCheckOut(Basket basket) {
        goToRandomProduct();
        addSingleProductToBasket(basket, 1);
        new ShoppingCartPopUpPage(driver).
                clickOnProceedToCheckOut();
        new BasketPage(driver).
                clickOnProceedToCheckOut();
        return this;
    }


}
