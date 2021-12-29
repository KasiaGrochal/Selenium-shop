package basket;

import basketStore.Basket;
import basketStore.OrderLine;
import basketStore.Product;
import handlers.FakeDataGenerator;
import model.Pages;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest extends Pages {

    @Test
    @Tag("shoppingCart")
    @Tag("regressionBig")
    void validateShoppingCartPopUpDisplayedInfo() {
        Basket currentBasket = new Basket();
        int randomQuantity = new FakeDataGenerator().getRandomNumberFromRange(1, 5);

        for (int i = 0; i < 1; i++) {
            topMenuPage.
                    goToRandomCategory();
            productsGridPage.
                    clickOnRandomProduct();
            OrderLine orderLine = new OrderLine(new Product(productDetailsPage.getProductName(), productDetailsPage.getDiscountPriceAsBigDecimal()), randomQuantity);
            productDetailsPage.
                    addSingleProductToBasket(currentBasket, randomQuantity);
            assertThat(orderLine).
                    isEqualToComparingFieldByFieldRecursively(shoppingCartPopUpPage.getOrderLineInfo());
            assertThat(shoppingCartPopUpPage.getThereIsXItemsInfoAsInt()).
                    isEqualTo(currentBasket.getBasketTotalQuantity());
            assertThat(shoppingCartPopUpPage.getTotalProductsValueAsBigDecimal()).
                    isEqualTo(currentBasket.getBasketTotalCost());
            shoppingCartPopUpPage.
                    clickOnContinueShoppingButton();
            assertThat(topMenuPage.getBasketProductCount()).isEqualTo(currentBasket.getBasketTotalQuantity());
        }

    }
}
