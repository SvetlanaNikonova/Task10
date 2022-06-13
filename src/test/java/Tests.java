import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.testng.AssertJUnit.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Tests {
    @Test
    public void testJSonParserWriteToFile(){

            JsonParser jsonParser = new JsonParser();
            jsonParser.writeToFile(new Cart("CartItem"));
            Path path = Paths.get("src/main/resources/CartItem.json");
            assertTrue(String.format("File %s doesn't exist", path), Files.exists(path));
    }

    @Test
    public void testJSonParserReadFromFile(){
        JsonParser jsonParser = new JsonParser();
        assertNotNull(jsonParser.readFromFile(new File("src/main/resources/CartItem.json")));
    }

    @Test
    public void testRealItem(){
        BigDecimal expectedResult = new BigDecimal(200);
        RealItem realItem = new RealItem();

        realItem.setWeight(200);
        assertEquals(String.format("Expected weight was %s, but actual was %s", expectedResult, realItem.getWeight()),realItem.getWeight(), expectedResult);
    }

    @Test
    public void testVirtualItem(){
        BigDecimal expectedResult = new BigDecimal(25.5);
        VirtualItem virtualItem = new VirtualItem();

        virtualItem.setSizeOnDisk(25.5);
        assertEquals(String.format("Expected size was %s, but actual was %s", expectedResult, virtualItem.getSizeOnDisk()), virtualItem.getSizeOnDisk(), expectedResult);
    }

    @Test
    public void testCart1(){
        String expectedResult = "CartTest1";
        assertEquals(String.format("Expected result was %s, but actual was %s", expectedResult, getCartName()), getCartName(), expectedResult);
    }

    private String getCartName(){
        Cart cart = new Cart("CartTest1");
        return cart.getCartName();
    }

    @Test
    public void testCart2(){
        BigDecimal expectedPrice = new BigDecimal(550.5 * 2 + (550.5 * 0.2) * 2);
        assertEquals(String.format("Expected price was %s, but actual was %s", expectedPrice, getCartTotalPrice("cart1", 550.5)), getCartTotalPrice("cart1", 550.5), expectedPrice);
    }

    @Test
    public void testTestCart() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            JsonParser jsonParser = new JsonParser();
            jsonParser.writeToFile(null);
        });
    }

    @Test
    public void testReadFromFile() {
        Assertions.assertThrows(NoSuchFileException.class, () -> {
            JsonParser jsonParser = new JsonParser();
            jsonParser.readFromFile(new File("testReadFromFile.txt"));
        });
    }

    private double getCartTotalPrice(String cartName, double price ){
        Cart cart = new Cart(cartName);
        RealItem realItem = new RealItem();
        realItem.setPrice(price);
        cart.addRealItem(realItem);
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setPrice(price);
        cart.addVirtualItem(virtualItem);
        return cart.getTotalPrice();
    }

    @Ignore
    @Test
    public void testCartGroupedAssertion(){
        assertAll("cart",
                ()-> assertEquals(getCartName(),"CartTest1"),
                () -> assertEquals(getCartTotalPrice("Cart1", (550.5 * 2 + (550.5 * 0.2) * 2)), (550.5 * 2 + (550.5 * 0.2) * 2))
                );
    }
}
