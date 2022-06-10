import org.junit.Ignore;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.testng.AssertJUnit.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Tests {
    @Test
    public void TestJSonParserWriteToFile(){

            JsonParser jsonParser = new JsonParser();
            jsonParser.writeToFile(new Cart("Belekas"));
            Path path = Paths.get("src//main//resources//Belekas.json");
            assertTrue(Files.exists(path));
    }

    @Test
    public void TestJSonParserReadFromFile(){
        JsonParser jsonParser = new JsonParser();
        assertNotNull(jsonParser.readFromFile(new File("src//main//resources//Belekas.json")));
    }

    @Test
    public void TestRealItem(){
        double expectedResult = 200;
        RealItem realItem = new RealItem();

        realItem.setWeight(200);
        assertEquals(realItem.getWeight(), expectedResult);
    }

    @Test
    public void TestVirtualItem(){
        double expectedResult = 25.5;
        VirtualItem virtualItem = new VirtualItem();

        virtualItem.setSizeOnDisk(25.5);
        assertEquals(virtualItem.getSizeOnDisk(), expectedResult);
    }

    @Test
    public void TestCart1(){
        String expectedResult = "CartTest1";
        assertEquals(GetCartName(),expectedResult);
    }

    private String GetCartName(){
        Cart cart = new Cart("CartTest1");
        return cart.getCartName();
    }

    @Test
    public void TestCart2(){
        double expectedPrice = 550.5 * 2 + (550.5 * 0.2) * 2;
        assertEquals(GetCartTotalPrice(),expectedPrice);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testTestCart() {
        JsonParser jsonParser = new JsonParser();
        jsonParser.writeToFile(null);
    }

    @Test(expectedExceptions = NoSuchFileException.class)
    public void testReadFromFile() {
        JsonParser jsonParser = new JsonParser();
        jsonParser.readFromFile(new File("testReadFromFile.txt"));
    }

    private double GetCartTotalPrice(){
        Cart cart = new Cart("CartTest2");
        RealItem realItem = new RealItem();
        realItem.setPrice(550.5);
        cart.addRealItem(realItem);
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setPrice(550.5);
        cart.addVirtualItem(virtualItem);
        return cart.getTotalPrice();
    }

    @Ignore
    @Test
    public void TestCartGroupedAssertion(){
        assertAll("cart",
                ()-> assertEquals(GetCartName(),"CartTest1"),
                () -> assertEquals(GetCartTotalPrice(),(550.5 * 2 + (550.5 * 0.2) * 2))
                );
    }

}
