package basket;

import basketStore.Basket;
import handlers.AddressFactory;
import handlers.UserFactory;
import model.Pages;
import models.Address;
import models.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckOutTest extends Pages {

    @Test
    @Tag("checkOut")
    @Tag("regressionSmall")
    void checkOutTest() {
        int maxProductQuantity = Integer.parseInt(System.getProperty("maxProductQuantityCheckOut"));
        int repeatTimes = Integer.parseInt(System.getProperty("addProductRepeatCheckout"));
        User randomUser = new UserFactory().getRandomUser();
        Address address = new AddressFactory().getRandomAddressPoland();
        Basket currentBasket = new Basket();
        String postAddress = randomUser.getFirstAndLastName() + "\n" + address.toString();
        String deliveryMethod = System.getProperty("deliveryOption");
        String paymentMethod = System.getProperty("paymentMethod");
        String country= System.getProperty("country");


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
        assertThat(checkOutPage.isPopUpTextEmpty()).isEqualTo(false);
        checkOutPage.
                closePopUp().
                clickOnTermsCheckBox().
                clickOnPlaceOrder();
        assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(orderConfirmationPage.getBasket());
        assertThat(orderConfirmationPage.getTrimmedDeliveryMethod()).contains(deliveryMethod);
        assertThat(orderConfirmationPage.getTrimmedPaymentMethod()).isEqualTo(paymentMethod);
        String referenceNumber = orderConfirmationPage.getTrimmedReferenceNumber();
        footerPage.
                clickOnOrdersButton();
        assertThat(orderHistoryPage.checkIfOrderIsOnTheList(referenceNumber)).isEqualTo(true);
        assertThat(orderHistoryPage.checkIfOrderLineIsCorrect(referenceNumber, currentBasket, paymentMethod)).isEqualTo(true);
        orderHistoryPage.
                clickOnDetails(referenceNumber);
        assertThat(currentBasket).isEqualToComparingFieldByFieldRecursively(orderHistoryDetailsPage.getBasket());
        assertThat(postAddress).isEqualTo(orderHistoryDetailsPage.getDeliveryAddress());
        assertThat(postAddress).isEqualTo(orderHistoryDetailsPage.getInvoiceAddress());

    }
}
