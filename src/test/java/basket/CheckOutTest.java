package basket;

import basketStore.Basket;
import handlers.AddressFactory;
import handlers.DateHandler;
import handlers.UserFactory;
import model.Pages;
import models.Address;
import models.User;
import orderSummary.OrderSummary;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.user.orderHistory.OrderHistoryLinePage;

public class CheckOutTest extends Pages {
    private static Logger logger = LoggerFactory.getLogger(CheckOutTest.class);

    @Test
    @Tag("checkOut")
    @Tag("regressionSmall")
    void checkOutTest() {
        SoftAssertions soft = new SoftAssertions();
        int maxProductQuantity = 3;
        int repeatTimes = 5;
        User randomUser = new UserFactory().getRandomUser();
        Address address = new AddressFactory().getRandomAddress();
        Basket currentBasket = new Basket();
        String postAddress = randomUser.getFirstAndLastName() + "\n" + address.toString();
        String deliveryMethod = System.getProperty("deliveryOption");
        String paymentMethod = System.getProperty("paymentMethod");
        String country= System.getProperty("country");
        String orderStatus = "Awaiting bank wire payment";

        registrationPage.
                registerNewUser(randomUser);
        productDetailsPage.
                addRandomProductsToBasket(currentBasket, repeatTimes, maxProductQuantity).
                addRandomProductToBasketAndGoToCheckOut(currentBasket);
        checkOutPage.
                fillInAddressDetailsForCountry(address,country).
                selectDeliveryMethod(deliveryMethod).
                selectPaymentMethod(paymentMethod).
                clickOnTermsOfService();
        soft.assertThat(checkOutPage.isPopUpTextEmpty()).isEqualTo(false);

        checkOutPage.
                closePopUp().
                clickOnTermsCheckBox().
                clickOnPlaceOrder();
        soft.assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(orderConfirmationPage.getBasket());
        soft.assertThat(orderConfirmationPage.getTrimmedDeliveryMethod()).contains(deliveryMethod);
        soft.assertThat(orderConfirmationPage.getTrimmedPaymentMethod()).isEqualTo(paymentMethod);
        String referenceNumber = orderConfirmationPage.getTrimmedReferenceNumber();

        footerPage.
                clickOnOrdersButton();
        soft.assertThat(checkIfOrderIsOnTheList(referenceNumber)).isEqualTo(true);

        OrderSummary expectedOrderSummary = new OrderSummary(DateHandler.getCurrentDateInMMddYYYY(),currentBasket.getBasketTotalCost(),paymentMethod,orderStatus);
        soft.assertThat(expectedOrderSummary).isEqualToComparingFieldByFieldRecursively(orderHistoryPage.getOrderLineByReferenceNumber(referenceNumber).toOrderSummary());

        orderHistoryPage.
                clickOnDetails(referenceNumber);
        soft.assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(orderHistoryDetailsPage.getBasket());
        soft.assertThat(postAddress).isEqualTo(orderHistoryDetailsPage.getDeliveryAddress());
        soft.assertThat(postAddress).isEqualTo(orderHistoryDetailsPage.getInvoiceAddress());
        soft.assertAll();
    }



    private boolean checkIfOrderIsOnTheList(String referenceNumber) {
        for (OrderHistoryLinePage line : orderHistoryPage.getListOfOrders()) {
            if (line.getReferenceNumber().equals(referenceNumber)) {
                return true;
            }
        }
        return false;
    }

}
