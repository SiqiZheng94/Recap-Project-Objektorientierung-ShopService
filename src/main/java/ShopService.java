import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.With;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
//@RequiredArgsConstructor
@Data
@Builder
@With
public class ShopService {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;

    public ShopService() {
        this.productRepo = new ProductRepo();
        this.orderRepo = new OrderMapRepo();
    }

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    /* public Order addOrder(List<String> productIds) {

                List<Product> products = new ArrayList<>();
                for (String productId : productIds) {
                    Optional<Product> productToOrder = productRepo.getProductById(productId);
                    if (productToOrder.isEmpty()) {
                        System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                        return null;
                    }

                    //products.add(productToOrder);
                    products.add(productToOrder.get());
                }
                Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.PROCESSING);

                return orderRepo.addOrder(newOrder);
            }

            */
    public Order addOrder(List<String> productIds) {

        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                throw new NoProductException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }

            //products.add(productToOrder);
            products.add(productToOrder.get());
        }


        Instant orderTimeStamp = Instant.now();
        Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.PROCESSING,orderTimeStamp);

        return orderRepo.addOrder(newOrder);


    }
   /* public List<Order> OrderPROCESSING(){
        return orderRepo.getOrders().stream()
                .filter(order -> order.status().equals(OrderStatus.PROCESSING))
                .collect(Collectors.toList());
    }
    */
    public List<Order> OrderStatusList(OrderStatus orderStatus){
        return orderRepo.getOrders().stream()
                .filter(order -> order.status().equals(orderStatus))
                .collect(Collectors.toList());
    }
    public Order updateOrder(String id, OrderStatus status){
        List<Order> orders = orderRepo.getOrders();
        Optional<Order> findOrder = orders.stream()
                .filter(order -> order.id().equals(id))
                .findFirst();
        if(findOrder.isPresent()){
            Order updatedorder = findOrder.get().withStatus(status);
            return updatedorder;
        }
        throw new NullPointerException("haven't find the order");
    }
    public Order getOldestOrderPerStatus(OrderStatus status){
        List<Order> orders = orderRepo.getOrders();
        Optional<Order> optionalOrder = orders.stream()
                .filter(order -> order.status().equals(status))
                .sorted(Comparator.comparing(Order::orderTimeStamp))
                .findFirst();
        if(optionalOrder.isEmpty()){
            throw new NullPointerException("there is no order in this status.");
        }
        return optionalOrder.get();

    }
}
