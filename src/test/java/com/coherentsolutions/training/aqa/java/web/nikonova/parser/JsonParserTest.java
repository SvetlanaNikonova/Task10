package com.coherentsolutions.training.aqa.java.web.nikonova.parser;

import com.coherentsolutions.training.aqa.java.web.nikonova.shop.RealItem;
import com.coherentsolutions.training.aqa.java.web.nikonova.shop.VirtualItem;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import com.coherentsolutions.training.aqa.java.web.nikonova.shop.Cart;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertAll;


public class JsonParserTest {
    private static JsonParser jsonParser;

    @BeforeAll
    public static void beforeAll() {
        jsonParser = new JsonParser();
    }

    @Test
    public void testJSonParserWriteToFile() {
        jsonParser.writeToFile(new Cart("CartItem"));
        Path path = Paths.get("src/test/resources/CartItem.json");
        Assertions.assertTrue(Files.exists(path), String.format("File %s doesn't exist", path));
    }

    @Ignore
    @Test
    public void testJSonParserReadFromFile() {
        Assertions.assertNotNull(jsonParser.readFromFile(new File("src/test/resources/CartItem.json")), "File doesn't exist");
    }

    @Test
    public void testReadFromFile() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            jsonParser.readFromFile(new File("testReadFromFile.txt"));
        });
    }

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

    @Test
    public void testWeightOfRealItem() {
        BigDecimal expectedResult = new BigDecimal(200);
        RealItem realItem = new RealItem();
        realItem.setWeight(expectedResult.doubleValue());
        Assertions.assertEquals(expectedResult.doubleValue(), realItem.getWeight(), "Weight is mismatched");
    }

    @Test
    public void testSizeOfVirtualItem() {
        BigDecimal expectedResult = new BigDecimal(25.5);
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(expectedResult.doubleValue());
        Assertions.assertEquals(virtualItem.getSizeOnDisk(), expectedResult.doubleValue(), "Size is mismatched");
    }
}
