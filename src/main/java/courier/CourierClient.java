package courier;

public class CourierClient extends RestAssuredClient{
    private final String ROOT = "/courier";
    private final String LOGIN = ROOT + "/login";
    private final String COURIER = ROOT + "/{courierId}";

    public boolean create (Courier courier){
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
    }

    public int login (CourierCredentials creds){
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .extract()
                .path("id");
    }
    public void delete (int courierId){
        reqSpec
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER)
                .then().log().all();

    }
    public String createDuplicate (Courier courier){
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(409)
                .extract()
                .path("message");
    }
    public String createWithoutLogin (Courier courier){
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    public int loginWithWrongCreds (CourierCredentials wrongCreds){
        return reqSpec
                .body(wrongCreds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .extract()
                .statusCode();
    }


}
