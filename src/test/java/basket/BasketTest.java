package basket;

import basketStore.Basket;
import handlers.FakeDataGenerator;
import model.Pages;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.basket.basketSummary.OrderLinePage;

import static org.assertj.core.api.Assertions.assertThat;


public class BasketTest extends Pages {

    @Test
    @Tag("basket")
    @Tag("regressionSmall")
    void validateAddRemoveBasketFunctionality() {

        int quantity = new FakeDataGenerator().getRandomNumberFromRange(1, 5);
        Basket currentBasket = new Basket();
        int orderLineIndex = 0;

        productDetailsPage.
                addRandomProductsToBasket(currentBasket, 5, quantity);
        topMenuPage.
                clickOnBasketIcon();
        assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(basketPage.getBasket());
        basketPage.
                setOrderLineQuantity("5", orderLineIndex, currentBasket);
        assertThat(basketPage.getOrderLineQuantity(orderLineIndex)).isEqualTo(currentBasket.getOrderLineQuantity(orderLineIndex));
        assertThat(basketPage.getTotalPriceTaxInclAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());
        basketPage.
                increaseOrderLineQuantityByClick(currentBasket, orderLineIndex);
        assertThat(basketPage.getOrderLineQuantity(orderLineIndex)).isEqualTo(currentBasket.getOrderLineQuantity(orderLineIndex));
        assertThat(basketPage.getTotalPriceTaxInclAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());
        basketPage.
                decreaseOrderLineQuantityByClick(currentBasket, orderLineIndex);
        assertThat(basketPage.getOrderLineQuantity(orderLineIndex)).isEqualTo(currentBasket.getOrderLineQuantity(orderLineIndex));
        assertThat(basketPage.getTotalPriceTaxInclAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());
        for (OrderLinePage orderLine : basketPage.getListOfOrderLines()) {
            basketPage.
                    clickOnTrashIcon(currentBasket, orderLineIndex);
            assertThat(basketPage.getTotalPriceTaxInclAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());
        }
        assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(basketPage.getBasket());

    }
}



