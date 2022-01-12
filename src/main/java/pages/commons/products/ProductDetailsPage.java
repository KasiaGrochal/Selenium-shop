package pages.commons.products;

import basketStore.Basket;
import handlers.FakeDataGenerator;
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
        return getText(discountPercentageInfo);
    }

    public boolean isRegularPriceDisplayed() {
        return regularPriceInfo.isDisplayed();
    }

    public boolean isDiscountPriceDisplayed() {
        return discountPriceInfo.isDisplayed();
    }

    public String getRegularPrice() {
        return getText(regularPriceInfo);
    }

    public BigDecimal getRegularPriceAsBigDecimal() {
        return FormatTextHandler.getBigDecimalFromString(getRegularPrice());
    }

    public String getDiscountPrice() {
        webDriverwait.until(x -> !getText(discountPriceInfo).isEmpty());
        return getText(discountPriceInfo);
    }

    public BigDecimal getDiscountPriceAsBigDecimal() {
        return FormatTextHandler.getBigDecimalFromString(getDiscountPrice());
    }


    public String getProductName() {
        return getText(productName);
    }

    public ProductDetailsPage addSingleProductToBasket(Basket basket, int quantity) {
        while (!addToCartButton.isEnabled()){
            goToRandomProduct();
        }
        send(quantityInput, String.valueOf(quantity));
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
    public ProductDetailsPage addRandomProductsToBasket(Basket basket, int repeat, int quantityMax) {
        for (int i = 0; i < repeat; i++) {
            goToRandomProduct();
            int minProductQuantity= Integer.parseInt(System.getProperty("minProductQuantity"));
            int quantity = new FakeDataGenerator().getRandomNumberFromRange(minProductQuantity, quantityMax);
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
