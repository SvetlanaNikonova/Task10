package parser;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import shop.Cart;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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
}
