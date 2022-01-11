package basket;

import basketStore.Basket;
import handlers.FakeDataGenerator;
import model.Pages;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest extends Pages {

    @Test
    @Tag("shoppingCart")
    @Tag("regressionBig")
    void validateShoppingCartPopUpDisplayedInfo() {
        Basket currentBasket = new Basket();
        SoftAssertions soft = new SoftAssertions();
        int maxProductQuantity = 5;
        int minProductQuantity = 1;

        for (int i = 0; i < 3; i++) {
            topMenuPage.
                    goToRandomCategory();
            productsGridPage.
                    clickOnRandomProduct();
            int randomQuantity = new FakeDataGenerator().getRandomNumberFromRange(minProductQuantity, maxProductQuantity);
            productDetailsPage.
                    addSingleProductToBasket(currentBasket, randomQuantity);
            String productName =shoppingCartPopUpPage.getProductName();
            soft.assertThat(currentBasket.getOrderLineByProductName(productName)).isEqualToComparingFieldByFieldRecursively(shoppingCartPopUpPage.getOrderLineInfo());
            soft.assertThat(shoppingCartPopUpPage.getThereIsXItemsInfoAsInt()).isEqualTo(currentBasket.getBasketTotalQuantity());
            soft.assertThat(shoppingCartPopUpPage.getTotalProductsValueAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());

            shoppingCartPopUpPage.
                    clickOnContinueShoppingButton();
            soft.assertThat(topMenuPage.getBasketProductCount()).isEqualTo(currentBasket.getBasketTotalQuantity());

        }
        soft.assertAll();
    }
}
