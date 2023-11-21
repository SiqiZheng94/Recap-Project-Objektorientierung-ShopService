import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductRepo productRepo = new ProductRepo();
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo,orderMapRepo);

        Product product2 = new Product("2", "bbb");
        Product product3 = new Product("3", "ccc");
        productRepo.addProduct(product2);
        productRepo.addProduct(product3);
        Order order1 = shopService.addOrder(List.of("1"));
        System.out.println(order1);
        Order order2 = shopService.addOrder(List.of("1","2"));
        System.out.println(order2);
        Order order3 = shopService.addOrder(List.of("1","2","3"));
        System.out.println(order3);

        System.out.println("the oldest order is: "+shopService.getOldestOrderPerStatus(OrderStatus.PROCESSING));
        //System.out.println(shopService.getOldestOrderPerStatus(OrderStatus.COMPLETED));
    }
}
