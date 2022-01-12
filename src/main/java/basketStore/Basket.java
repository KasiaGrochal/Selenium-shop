package basketStore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Basket {

    public List<OrderLine> getBasketLists() {
        return basketLists;
    }

   private List<OrderLine> basketLists;
   private int totalQuantity;
   private BigDecimal totalCost;

    public Basket() {
        basketLists = new ArrayList<>();
        totalQuantity = getBasketTotalQuantity();
        totalCost = getBasketTotalCost();
    }

    public void addProductToBasket(String productName, BigDecimal price, int quantity){
        for (OrderLine orderLine: basketLists){
            if (isProductAlreadyInTheBasket(productName,orderLine.getProduct())){
                orderLine.setQuantity(orderLine.getQuantity()+quantity);
                orderLine.setTotalCost(BigDecimal.valueOf(orderLine.getQuantity()).multiply(orderLine.getProduct().getPrice()));
                return;
            }
        }
        addNewProductToBasket(new Product(productName,price),quantity);
    }

    public boolean isProductAlreadyInTheBasket(String productName, Product product){
        return product.getName().equals(productName);
    }

    public void addNewProductToBasket(Product product, int quantity){
        basketLists.add(new OrderLine(product,quantity));
    }

    public void addOrderLineToBasket(OrderLine orderLine){
        basketLists.add(orderLine);
    }

    public BigDecimal getBasketTotalCost(){
        return basketLists.stream()
                .map(OrderLine::getTotalCost)
                .reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);
    }

    public int getBasketTotalQuantity(){
        return basketLists.stream().mapToInt(OrderLine::getQuantity).sum();
    }

    public void deleteOrderLine(int orderLineIndex){
        basketLists.remove(basketLists.get(orderLineIndex));
    }

    public OrderLine getOrderLineByProductName(String productName){
        return getBasketLists().stream().
                filter(x->x.getProduct().getName().equals(productName)).
                collect(Collectors.toList()).
                get(0);
    }


}
