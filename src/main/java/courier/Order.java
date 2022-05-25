package courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public static Order getRandom() {
        String firstName = RandomStringUtils.randomAlphanumeric(10);
        String lastName = RandomStringUtils.randomAlphanumeric(10);
        String address = RandomStringUtils.randomAlphanumeric(100);
        String metroStation = RandomStringUtils.randomAlphanumeric(20);
        String phone = RandomStringUtils.randomAlphanumeric(10);
        int rentTime = (int) Math.random();
        String deliveryDate = RandomStringUtils.randomAlphanumeric(11);
        String comment = RandomStringUtils.randomAlphanumeric(100);
        List<String> color = Arrays.asList("BLACK", "GREY");

        return new Order();

    }


}
