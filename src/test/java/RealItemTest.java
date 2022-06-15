import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import shop.RealItem;

import java.math.BigDecimal;

public class RealItemTest {
    @Test
    public void testWeightOfRealItem() {
        BigDecimal expectedResult = new BigDecimal(200);
        RealItem realItem = new RealItem();
        realItem.setWeight(expectedResult.doubleValue());
        Assertions.assertEquals(expectedResult.doubleValue(), realItem.getWeight(), "Weight is mismatched");
    }
}
