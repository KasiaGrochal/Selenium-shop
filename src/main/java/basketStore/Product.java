package basketStore;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    String name;
    BigDecimal price;

    public Product(String name, BigDecimal price) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(price));
        this.name = name;
        this.price = bigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Basketttttt.Product: " + name +
                ", Price: " + price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
