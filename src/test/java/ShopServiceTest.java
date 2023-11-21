import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        //Order expected = new Order("-1", List.of(new Product("1", "Apfel")),OrderStatus.IN_DELIVERY,actual.orderTimeStamp());
        //assertEquals(expected.products(),actual.products());
        Order expected = new Order(actual.id(), List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING,actual.orderTimeStamp());
        assertEquals(expected,actual);
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        try {
            shopService.addOrder(productsIds);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        //THEN

    }
    @Test
    void OrderStatusList_whenPROCESSING_exceptEquals(){

        ShopService shopService = new ShopService();
        shopService.addOrder(List.of("1"));
        List<Order> orderStatusProcessing = shopService.OrderStatusList(OrderStatus.PROCESSING);
        for(Order order:orderStatusProcessing) {
            assertEquals(order.status(),OrderStatus.PROCESSING);
        }
    }
    @Test
    void OrderStatusList_whenCOMPLETED_except0(){

        ShopService shopService = new ShopService();
        shopService.addOrder(List.of("1"));
        List<Order> orderStatusCompleted = shopService.OrderStatusList(OrderStatus.COMPLETED);
        assertEquals(0,orderStatusCompleted.size());
    }
    @Test
    void updateOrder_when(){
        ShopService shopService = new ShopService();
        //Product part1 = new Product("1", "part1");
        //Product part2 = new Product("2", "part2");

        Order order1 = shopService.addOrder(List.of("1"));
        Order actual = shopService.updateOrder(order1.id(),OrderStatus.IN_DELIVERY);
        Order excepted = new Order(order1.id(), order1.products(), OrderStatus.IN_DELIVERY,actual.orderTimeStamp());
        assertEquals(actual,excepted);
    }
}
