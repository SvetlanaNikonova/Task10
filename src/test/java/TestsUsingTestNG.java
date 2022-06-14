import org.junit.Ignore;
import org.testng.annotations.Test;
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

import static org.testng.Assert.*;

public class TestsUsingTestNG{
        @Test
        public void testJSonParserWriteToFile(){
            JsonParser jsonParser = new JsonParser();
            jsonParser.writeToFile(new Cart("CartItem"));
            Path path = Paths.get("src/main/resources/CartItem.json");
            assertTrue(Files.exists(path), String.format("File %s doesn't exist", path));
        }

        @Test
        public void testJSonParserReadFromFile(){
            JsonParser jsonParser = new JsonParser();
            assertNotNull(jsonParser.readFromFile(new File("src/main/resources/CartItem.json")),"File doesn't exist");
        }

        @Test
        public void testWeightOfRealItem(){
            BigDecimal expectedResult = new BigDecimal(200);
            RealItem realItem = new RealItem();

            realItem.setWeight(200);
            assertEquals(expectedResult,realItem.getWeight() ,"Weight is mismatched");
        }

        @Test
        public void testSizeOfVirtualItem(){
            BigDecimal expectedResult = new BigDecimal(25.5);
            VirtualItem virtualItem = new VirtualItem();

            virtualItem.setSizeOnDisk(25.5);
            assertEquals(virtualItem.getSizeOnDisk(), expectedResult, "Size is mismatched");
        }

        @Test
        public void testCartName(){
            String expectedResult = "CartTest1";
            assertEquals(getCartName(), expectedResult, "Result is mismatched");
        }

        private String getCartName(){
            Cart cart = new Cart("CartTest1");
            return cart.getCartName();
        }

        @Test
        public void testCartTotalPrice(){
            BigDecimal expectedPrice = new BigDecimal(550.5 * 2 + (550.5 * 0.2) * 2);
            assertEquals(getCartTotalPrice("cart1", 550.5), expectedPrice, "Price is mismatched");
        }

        @Test
        public void testCartForNull() {
            assertThrows(NullPointerException.class, () -> {
                JsonParser jsonParser = new JsonParser();
                jsonParser.writeToFile(null);
            });
        }

        @Test
        public void testReadFromFile() {
            assertThrows(NoSuchFileException.class, () -> {
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
/*
        @Ignore
        @Test
        public void testCartGroupedAssertion(){
            assertAll("cart",
                    ()-> assertEquals(getCartName(),"CartTest1"),
                    () ->assertEquals(getCartTotalPrice("Cart1", (550.5 * 2 + (550.5 * 0.2) * 2)), (550.5 * 2 + (550.5 * 0.2) * 2))
            );
        }  */
    }





