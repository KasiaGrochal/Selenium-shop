package basket;

import basketStore.Basket;
import basketStore.OrderLine;
import basketStore.Product;
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
        int randomQuantity = new FakeDataGenerator().getRandomNumberFromRange(1, 5);
        SoftAssertions soft = new SoftAssertions();

        for (int i = 0; i < 1; i++) {
            topMenuPage.
                    goToRandomCategory();
            productsGridPage.
                    clickOnRandomProduct();
            OrderLine orderLine = new OrderLine(new Product(productDetailsPage.getProductName(), productDetailsPage.getDiscountPriceAsBigDecimal()), randomQuantity);
            productDetailsPage.
                    addSingleProductToBasket(currentBasket, randomQuantity);
            soft.assertThat(orderLine).isEqualToComparingFieldByFieldRecursively(shoppingCartPopUpPage.getOrderLineInfo());
            soft.assertThat(shoppingCartPopUpPage.getThereIsXItemsInfoAsInt()).isEqualTo(currentBasket.getBasketTotalQuantity());
            soft.assertThat(shoppingCartPopUpPage.getTotalProductsValueAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());
            shoppingCartPopUpPage.
                    clickOnContinueShoppingButton();
            soft.assertThat(topMenuPage.getBasketProductCount()).isEqualTo(currentBasket.getBasketTotalQuantity());
            soft.assertAll();
        }

    }
}
