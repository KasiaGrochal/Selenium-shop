package basketStore;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Product {
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(price));
        this.name = name;
        this.price = bigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

}
