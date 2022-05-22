package courierflow;

import courier.CourierClient;
import courier.Order;
import courier.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTest {

    private List<String> color;
    private int expected;
    private OrderClient orderClient;


    @Before
    public void setup() {
        orderClient = new OrderClient();
    }


    public OrderTest(List<String> color, int expected) {
        this.color = color;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] createOrderWithColor() {
        return new Object[][]{
                {List.of("BLACK"), 201},
                {List.of("GREY"), 201},
                {List.of("BLACK", "GREY"), 201},
                {null, 201},
        };
    }

    @Test
    @DisplayName("Check order creation")
    @Description("Basic test for /api/v1/orders")
    public void createOrder() {
        Order order = Order.getRandom();
        int expected = 201;
        int actual = orderClient.create(order);
        assertEquals("Некорректный код ответа", expected, actual);

    }


}
