package basketStore;

import java.math.BigDecimal;

public class OrderLine {

    Product product;
    int quantity;
    BigDecimal totalCost;


    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalCost = product.getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }

    public void updateTotalCost(){
        this.totalCost = product.getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
