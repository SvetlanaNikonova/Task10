package shop;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import parser.JsonParser;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CartTest {

    @Test
    public void testCartName() {
        String expectedResult = "shop.CartTest";
        Assertions.assertEquals(getCartName("shop.CartTest"), expectedResult, "Result is mismatched");
    }

    private String getCartName(String cartName) {
        Cart cart = new Cart(cartName);
        return cart.getCartName();
    }

    @Test
    public void testCartTotalPrice() {
        BigDecimal expectedPrice = new BigDecimal(550.5 * 2 + (550.5 * 0.2) * 2);
        Assertions.assertEquals(getCartTotalPrice("shop.CartTest", 550.5), expectedPrice.doubleValue(), "Price is mismatched");
    }

    @Test
    public void testCartForNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            JsonParser jsonParser = new JsonParser();
            jsonParser.writeToFile(null);
        });
    }

    private double getCartTotalPrice(String cartName, double price) {
        Cart cart = new Cart(cartName);
        RealItem realItem = new RealItem();
        realItem.setPrice(price);
        cart.addRealItem(realItem);
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setPrice(price);
        cart.addVirtualItem(virtualItem);
        return cart.getTotalPrice();
    }

    @Test
    public void testCartGroupedAssertion() {
        BigDecimal expectedPrice = new BigDecimal(550.5 * 2 + (550.5 * 0.2) * 2);
        assertAll("cart",
                () -> Assertions.assertEquals(getCartName("shop.CartTest"), "shop.CartTest"),
                () -> Assertions.assertEquals(getCartTotalPrice("shop.CartTest", 550.5), expectedPrice.doubleValue())
        );
    }
}