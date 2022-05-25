package courierflow;

import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierLoginTest {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setup() {
        courierClient = new CourierClient();
    }

    @After
    public void teardown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Check authorization of courier")
    @Description("Basic test for /api/v1/courier/login")
    public void courierCreateTest() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.create(courier);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);

        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Check authorization with wrong password")
    @Description("Negative test for /api/v1/courier/login")
    public void courierWrongPasswordTest() {
        Courier courier = new Courier("wright", "wright", "name");
        boolean created = courierClient.create(courier);
        CourierCredentials wrongCreds = new CourierCredentials("wright", "wrong");

        int actual = courierClient.loginWithWrongCreds(wrongCreds);
        int expected = 404;
        assertEquals("Некорректный код ошибки", expected, actual);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
    }

    @Test
    @DisplayName("Check authorization without login")
    @Description("Negative test for /api/v1/courier/login")
    public void courierNullLoginTest() {
        Courier courier = new Courier("wright", "wright", "name");
        boolean created = courierClient.create(courier);
        CourierCredentials wrongCreds = new CourierCredentials(null, "wrong");

        int actual = courierClient.loginWithWrongCreds(wrongCreds);
        int expected = 400;
        assertEquals("Некорректный код ошибки", expected, actual);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
    }

    @Test
    @DisplayName("Check authorization without fake credentials")
    @Description("Negative test for /api/v1/courier/login")
    public void courierNotExistingTest() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.create(courier);
        CourierCredentials wrongCreds = new CourierCredentials("blablabla", "blablabla");

        int actual = courierClient.loginWithWrongCreds(wrongCreds);
        int expected = 404;
        assertEquals("Некорректный код ошибки", expected, actual);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
    }
}
