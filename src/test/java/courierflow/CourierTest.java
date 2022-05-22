package courierflow;

import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.then;


public class CourierTest {

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
    @DisplayName("Check creation of courier")
    @Description("Basic test for /api/v1/courier")
    public void courierCreateTest() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.create(courier);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);

        assertTrue(created);
    }

    @Test
    @DisplayName("Check creation of two same couriers")
    @Description("Negative test for /api/v1/courier")
    public void courierDuplicateTest() {
        Courier courier = new Courier("rtyurty", "rtyurty", "rtyurty");
        boolean created = courierClient.create(courier);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);


        String actual = courierClient.createDuplicate(courier);
        String expected = "Этот логин уже используется.";
        assertEquals("Ответ не соответствует документации", expected, actual);
    }

    @Test
    @DisplayName("Check creation without login")
    @Description("Negative test for /api/v1/courier")
    public void courierWithoutLoginTest() {
        Courier courier = new Courier("withoutpassword", "withoutpassword");
        String actual = courierClient.createWithoutLogin(courier);
        String expected = "Недостаточно данных для создания учетной записи";

        assertEquals("Ответ не соответствует документации", expected, actual);
    }

    @Test
    @DisplayName("Check creation without login")
    @Description("Negative test for /api/v1/courier")
    public void courierDuplicateLoginTest() {
        Courier courier = new Courier("rtyurty", "wewewe", "wewewew");
        boolean created = courierClient.create(courier);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);


        String actual = courierClient.createDuplicate(courier);
        String expected = "Этот логин уже используется.";
        assertEquals("Ответ не соответствует документации", expected, actual);
    }
}




