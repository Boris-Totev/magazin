import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testPrintAvailableProducts() {
        // GIVEN
        Product.products.clear();
        Product product1 = new Product(1, "Product1", 2.2, 200, ProductType.FOOD, "red", "03-03-2024");
        Product product2 = new Product(2, "Product2", 2.5, 0, ProductType.OTHERS, "white", "03-05-2024");
        Product product3 = new Product(3, "Product3", 4.2, 400, ProductType.MAKEUP, "green", "06-07-2025");
        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);

        // WHEN
        Customer.printAvailableProducts();

        // THEN
        String expectedOutput = "Available Products:\n" + "\n"+"Product ID: 1   Product Name: Product1   Price: 2.2   Quantity: 200   Type: FOOD   Color: red   Expiration Date: 03-03-2024\n"
                +"Product ID: 3   Product Name: Product3   Price: 4.2   Quantity: 400   Type: MAKEUP   Color: green   Expiration Date: 06-07-2025";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testSearchProductByName() {
        // GIVEN
        Product product1 = new Product(1, "Product1", 2.2, 200, ProductType.FOOD, "red", "03-03-2024");
        Product product2 = new Product(2, "Product2", 2.5, 0, ProductType.OTHERS, "white", "03-05-2024");
        Product product3 = new Product(3, "Product3", 4.2, 400, ProductType.MAKEUP, "green", "06-07-2025");
        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // WHEN
        Customer.searchProductByName();

        // THEN
        String expectedOutput = "Available Products:\n" + "\n"+"Product ID: 1   Product Name: Product1   Price: 2.2   Quantity: 200   Type: FOOD   Color: red   Expiration Date: 03-03-2024\n"
                +"Product ID: 3   Product Name: Product3   Price: 4.2   Quantity: 400   Type: MAKEUP   Color: green   Expiration Date: 06-07-2025";;
        assertEquals(expectedOutput, outContent.toString().trim());

    }
}
