package courier;

import java.util.ArrayList;

public class OrderClient extends RestAssuredClient {

    private final String ROOT = "/orders";
    //private final String LOGIN = ROOT + "/login";
    //private final String COURIER = ROOT + "/{courierId}";

    public int create(Order order) {
        return reqSpec
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .statusCode();

    }


    public ArrayList ordersList() {
        return reqSpec
                //.body()
                .when()
                .get(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("orders");
    }

}
