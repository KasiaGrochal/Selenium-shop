package products;

import model.Pages;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.commons.products.ProductBoxPage;
import pages.commons.products.ProductsGridPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PricesDropTest extends Pages {

    @Test
    @Tag("priceDrop")
    @Tag("regressionSmall")
    void priceDropDisplayedInfoValidation() {

        footerPage.
                clickOnPricesDropButton();
        assertThat(onSalePage.getHeaderText(), equalTo(System.getProperty("onSaleHeaderText")));

        List<ProductBoxPage> listOfProducts = new ProductsGridPage(webdriver).getListOfProducts();
        for (ProductBoxPage product : listOfProducts) {
            assertThat(product.getDiscountFlagValue(), equalTo(System.getProperty("discountFlagValue")));
            assertThat(product.isRegularPriceVisible(), equalTo(true));
            assertThat(product.isDiscountPriceVisible(), equalTo(true));
            assertThat(isDiscountCalculatedCorrectly(
                            product.getRegularPriceAsBigDecimal(), product.getDiscountPriceAsBigDecimal()),
                    equalTo(true));
        }

        productsGridPage.
                clickOnRandomProduct();
        assertThat(productDetailsPage.getDiscountPercentageInfo(), equalTo(System.getProperty("discountPercentageValue")));
        assertThat(productDetailsPage.isRegularPriceDisplayed(), equalTo(true));
        assertThat(productDetailsPage.isDiscountPriceDisplayed(), equalTo(true));
        assertThat(isDiscountCalculatedCorrectly(
                productDetailsPage.getRegularPriceAsBigDecimal(), productDetailsPage.getDiscountPriceAsBigDecimal()),
                equalTo(true));

    }

    public static boolean isDiscountCalculatedCorrectly(BigDecimal regularPrice, BigDecimal discountPrice) {
        BigDecimal discountAmount = regularPrice.multiply(BigDecimal.valueOf(0.2)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountShouldBe = regularPrice.subtract(discountAmount);
        return discountShouldBe.equals(discountPrice);
    }
}
