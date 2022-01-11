package basket;

import basketStore.Basket;
import model.Pages;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.basket.basketSummary.OrderLinePage;


public class BasketTest extends Pages {

    @Test
    @Tag("basket")
    @Tag("regressionSmall")
    void validateAddRemoveBasketFunctionality() {
        SoftAssertions soft = new SoftAssertions();
        int maxProductQuantity = 5;
        String modifyQuantityTo = "5";
        int repeatTimes = 5;
        Basket currentBasket = new Basket();
        int orderLineToModify = 0;

        productDetailsPage.
                addRandomProductsToBasket(currentBasket, repeatTimes, maxProductQuantity);
        topMenuPage.
                clickOnBasketIcon();
        soft.assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(basketPage.getBasket());
        soft.assertThat(basketPage.getTotalPriceTaxInclAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());

        basketPage.
                setOrderLineQuantity(modifyQuantityTo, orderLineToModify, currentBasket);
        soft.assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(basketPage.getBasket());
        soft.assertThat(basketPage.getTotalPriceTaxInclAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());

        basketPage.
                increaseOrderLineQuantityByClick(currentBasket, orderLineToModify);
        soft.assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(basketPage.getBasket());
        soft.assertThat(basketPage.getTotalPriceTaxInclAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());

        basketPage.
                decreaseOrderLineQuantityByClick(currentBasket, orderLineToModify);
        soft.assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(basketPage.getBasket());
        soft.assertThat(basketPage.getTotalPriceTaxInclAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());

        for (OrderLinePage orderLine : basketPage.getListOfOrderLines()) {
            basketPage.
                    clickOnTrashIcon(currentBasket, orderLineToModify);
            soft.assertThat(basketPage.getTotalPriceTaxInclAsBigDecimal()).isEqualTo(currentBasket.getBasketTotalCost());
        }
        soft.assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(basketPage.getBasket());
        soft.assertAll();
    }
}



