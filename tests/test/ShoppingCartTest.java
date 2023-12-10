import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        Product.products.clear();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testAddToShoppingCart() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 10, ProductType.SANITARY, "", "");

        Product.products = new ArrayList<>();
        Product.products.add(product1);
        Product.products.add(product2);

        String input = "2\n" + "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        System.setOut(new PrintStream(outContent));

        ShoppingCart.addToShoppingCart();

        String expectedOutput = "Enter product ID to add to the shopping cart: Enter product quantity: Product added to the shopping cart successfully." +
                System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());

        // Verify that the product is added to the shopping cart
        //assertTrue(ShoppingCart.getProductsInCart().contains(product2));
        //assertEquals(3, ShoppingCart.getProductsInCart().get(ShoppingCart.getProductsInCart().indexOf(product2)).getQuantity()); // Quantity in the shopping cart
    }           //

    @Test
    void testGetProductById() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 10, ProductType.SANITARY, "", "");

        Product.products = new ArrayList<>();
        Product.products.add(product1);
        Product.products.add(product2);

        Product retrievedProduct = ShoppingCart.getProductById(2);

        assertNotNull(retrievedProduct);
        assertEquals(product2, retrievedProduct);
    }

    @Test
    void testCalculateTotalPrice() {
        // Arrange
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 10, ProductType.SANITARY, "", "");
        ShoppingCart.getProductsInCart().clear(); // Clear the shopping cart before the test

        // Act
        ShoppingCart.addProduct(product1, 3);
        ShoppingCart.addProduct(product2, 2);

        // Assert
        double totalPrice = ShoppingCart.calculateTotalPrice();
        assertEquals(5.3, totalPrice, 0.001); // Specify a delta for floating-point comparison
    }
}

