package pages.basket.checkOut;

import models.Address;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.basket.basketSummary.BasketPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        listOfDeliveryMethods.forEach(x->list.add(new DeliveryMethodsPage(x,driver)));
        return list;
    }

    public CheckOutPage selectDeliveryMethod(String deliveryMethod){
        getListOfDeliveryMethods().stream().
                filter(x -> x.getDeliveryMethodName().equals(deliveryMethod)).
                collect(Collectors.toList()).
                get(0).
                clickOnDeliveryMethod();
        waitForElementToBeVisibleFluent(deliveryContinueButton);
        deliveryContinueButton.click();
        return this;
    }

    public CheckOutPage fillInAddressDetailsForCountry(Address address, String country) {
        send(streetInput, address.getStreet());
        send(zipCodeInput, address.getZipCode());
        send(cityInput, address.getCity());
        selectSpecificCountry(country);
        noStaleClick(addressContinueButton);
        return this;
    }

    public CheckOutPage selectSpecificCountry(String country) {
        Select select = new Select(selectCountry);
        select.selectByVisibleText(country);
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
        click(termsOfServiceLinkButton);
        return this;
    }

    public boolean isPopUpTextEmpty(){
        waitForElementToBeVisibleFluent(modalContent);
        return modalContent.getText().isEmpty();
    }

    public CheckOutPage closePopUp(){
        click(closeModalButton);
        return this;
    }

    public CheckOutPage clickOnTermsCheckBox(){
        waitForWebElementToBeClickable(termsOfServiceLinkButton);
        clickOnCheckBox(termsCheckbox);
        return this;
    }

    public CheckOutPage clickOnPlaceOrder(){
        click(placeOrderButton);
        return this;
    }


}
