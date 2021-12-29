package model;

import org.junit.jupiter.api.BeforeEach;
import pages.OnSalePage;
import pages.basket.ShoppingCartPopUpPage;
import pages.basket.basketSummary.BasketPage;
import pages.basket.checkOut.CheckOutPage;
import pages.basket.checkOut.OrderConfirmationPage;
import pages.categories.CategoriesPage;
import pages.commons.FiltersPage;
import pages.commons.FooterPage;
import pages.commons.TopMenuPage;
import pages.commons.products.ProductDetailsPage;
import pages.commons.products.ProductsGridPage;
import pages.user.AccountPage;
import pages.user.LoginPage;
import pages.user.RegistrationPage;
import pages.user.orderHistory.OrderHistoryPage;
import pages.user.orderHistory.details.OrderHistoryDetailsPage;

public class Pages extends TestBase {
    public BasketPage basketPage;
    public ProductsGridPage productsGridPage;
    public CheckOutPage checkOutPage;
    public OrderConfirmationPage orderConfirmationPage;
    public ShoppingCartPopUpPage shoppingCartPopUpPage;
    public CategoriesPage categoriesPage;
    public ProductDetailsPage productDetailsPage;
    public FiltersPage filtersPage;
    public FooterPage footerPage;
    public TopMenuPage topMenuPage;
    public AccountPage accountPage;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;
    public OnSalePage onSalePage;
    public OrderHistoryPage orderHistoryPage;
    public OrderHistoryDetailsPage orderHistoryDetailsPage;


    @BeforeEach
    public void setUpPages() {
        basketPage = new BasketPage(webdriver);
        checkOutPage = new CheckOutPage(webdriver);
        orderConfirmationPage = new OrderConfirmationPage(webdriver);
        shoppingCartPopUpPage = new ShoppingCartPopUpPage(webdriver);
        categoriesPage = new CategoriesPage(webdriver);
        productDetailsPage = new ProductDetailsPage(webdriver);
        productsGridPage = new ProductsGridPage(webdriver);
        filtersPage = new FiltersPage(webdriver);
        footerPage = new FooterPage(webdriver);
        topMenuPage = new TopMenuPage(webdriver);
        accountPage = new AccountPage(webdriver);
        loginPage = new LoginPage(webdriver);
        registrationPage = new RegistrationPage(webdriver);
        onSalePage = new OnSalePage(webdriver);
        orderHistoryPage = new OrderHistoryPage(webdriver);
        orderHistoryDetailsPage = new OrderHistoryDetailsPage(webdriver);
    }


}
