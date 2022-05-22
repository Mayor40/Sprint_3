package courierflow;

import courier.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;


public class OrderListTest {

    private OrderClient orderClient;

    @Before
    public void setup() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Check order list creation")
    @Description("Basic test for /api/v1/orders")
    public void ordersListTest() {
        ArrayList gotList = orderClient.ordersList();
    }


}
