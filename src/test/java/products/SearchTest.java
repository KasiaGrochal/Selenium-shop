package products;

import model.Pages;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class SearchTest extends Pages {

    @Test
    @Tag("search")
    @Tag("regressionBig")
    void validateIfSearchedProductIsDisplayedInSearchResults() {
        String randomName = productsGridPage.getRandomProductName();

        topMenuPage.
                typeInSearchBox(randomName).
                clickOnSearchIcon();
        assertThat(productsGridPage.isProductOnTheList(randomName), equalTo(true));
    }

    @Test
    @Tag("search")
    @Tag("regressionBig")
    void validateDropDownSuggestions() {
        String randomName = productsGridPage.getRandomProductName();

        topMenuPage.
                typeInSearchBox(randomName);
        assertThat(topMenuPage.isSearchedProductOnTheDropdownList(randomName), equalTo(true));

    }


}
