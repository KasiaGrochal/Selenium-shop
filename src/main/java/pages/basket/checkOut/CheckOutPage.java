package pages.basket.checkOut;

import models.Address;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.basket.basketSummary.BasketPage;

import java.util.ArrayList;
import java.util.List;

public class CheckOutPage extends BasketPage {
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[name='address1']")
    private WebElement streetInput;

    @FindBy(css = "[name='city']")
    private WebElement cityInput;

    @FindBy(css = "[name='postcode']")
    private WebElement zipCodeInput;

    @FindBy(css = ".js-country")
    private WebElement selectCountry;

    @FindBy(css = ".form-footer>button")
    private WebElement addressContinueButton;

    @FindBy(css = "#js-delivery>button")
    private WebElement deliveryContinueButton;

    @FindBy(css = "#payment-option-2")
    private WebElement payByBankWireOption;

    @FindBy(css = "#payment-option-1")
    private WebElement payByCheck;


    @FindBy(css = ".js-terms>a")
    private WebElement termsOfServiceLinkButton;

    @FindBy (css = ".modal-content>button")
    private WebElement closeModalButton;

    @FindBy (css = ".js-modal-content")
    private WebElement modalContent;

    @FindBy (css = "[id='conditions_to_approve[terms-and-conditions]']")
    private WebElement termsCheckbox;

    @FindBy (css = ".ps-shown-by-js>button")
    private WebElement placeOrderButton;

    @FindBy (css = "#checkout-delivery-step")
    private WebElement deliveryView;

    @FindBy(css = ".delivery-option")
    private List<WebElement> listOfDeliveryMethods;



    public List<DeliveryMethodsPage> getListOfDeliveryMethods() {
        webDriverwait.until(x-> ExpectedConditions.attributeContains(deliveryView,"class","-current"));
        List<DeliveryMethodsPage> list = new ArrayList<>();
        for (WebElement deliveryMethod : listOfDeliveryMethods) {
            list.add(new DeliveryMethodsPage(deliveryMethod, driver));
        }
        return list;
    }

    public CheckOutPage selectDeliveryMethod(String deliveryMethod){
        for (DeliveryMethodsPage deliveryMethodsPage: getListOfDeliveryMethods()){
            if(deliveryMethodsPage.getDeliveryMethodName().equals(deliveryMethod)){
                    deliveryMethodsPage.clickOnDeliveryMethod();
                    break;
            }
        }
        waitForElementToBeVisibleFluent(deliveryContinueButton);
        deliveryContinueButton.click();
        return this;
    }


    public CheckOutPage fillInAddressDetails(Address address) {
        sendKeysToObject(streetInput, address.getStreet());
        sendKeysToObject(zipCodeInput, address.getZipCode());
        sendKeysToObject(cityInput, address.getCity());
        selectRandomCountry();
        noStaleClick(addressContinueButton);
        return this;
    }

    public CheckOutPage selectRandomCountry() {
        selectRandomOption(selectCountry, 1);
        return this;
    }

    public CheckOutPage selectPaymentMethod(String paymentMethod){
        waitForWebElementToBeClickable(termsOfServiceLinkButton);
        switch (paymentMethod){
            case "Bank transfer":
                clickOnCheckBox(payByBankWireOption);
                break;
            case "Payments by check":
                clickOnCheckBox(payByCheck);
                break;
        }
        return this;
    }

    public CheckOutPage clickOnTermsOfService(){
        clickOnButton(termsOfServiceLinkButton);
        return this;
    }

    public boolean isPopUpTextEmpty(){
        waitForElementToBeVisibleFluent(modalContent);
        return modalContent.getText().isEmpty();
    }

    public CheckOutPage closePopUp(){
        clickOnButton(closeModalButton);
        return this;
    }

    public CheckOutPage clickOnTermsCheckBox(){
        waitForWebElementToBeClickable(termsOfServiceLinkButton);
        clickOnCheckBox(termsCheckbox);
        return this;
    }

    public CheckOutPage clickOnPlaceOrder(){
        clickOnButton(placeOrderButton);
        return this;
    }


}
