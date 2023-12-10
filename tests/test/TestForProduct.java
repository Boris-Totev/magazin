import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestForProduct {

    //private final InputStream originalIn = System.in;
    //private final PrintStream originalOut = System.out;
    //private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


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
    void testPrintAllProducts() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        System.setOut(new PrintStream(outContent));

        // Act
        Product.printAllProducts();

        // Restore the original System.out
        System.setOut(System.out);

        // Assert
        String expectedOutput =
                "Product ID: 1   Product Name: oranges   Price: 0.1   Quantity: 500   Type: food   Color:    Expiration Date: 06-07-2021" + System.lineSeparator() +
                        "Product ID: 2   Product Name: toilet paper   Price: 2.5   Quantity: 150   Type: sanitary   Color:    Expiration Date: " + System.lineSeparator() +
                        "Product ID: 3   Product Name: lipstick   Price: 6.8   Quantity: 15   Type: makeup   Color: red   Expiration Date: 04-02-2025" + System.lineSeparator() +
                        "Product ID: 4   Product Name: dog leash   Price: 10.3   Quantity: 6   Type: others   Color: blue   Expiration Date: " + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintProductsSortedByName() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        System.setOut(new PrintStream(outContent));

        Product.printProductsSortedByName();

        System.setOut(System.out);

        String expectedOutput =
                """
                        Product ID: 4   Product Name: dog leash   Price: 10.3   Quantity: 6   Type: others   Color: blue   Expiration Date:\s
                        Product ID: 3   Product Name: lipstick   Price: 6.8   Quantity: 15   Type: makeup   Color: red   Expiration Date: 04-02-2025
                        Product ID: 1   Product Name: oranges   Price: 0.1   Quantity: 500   Type: food   Color:    Expiration Date: 06-07-2021
                        Product ID: 2   Product Name: toilet paper   Price: 2.5   Quantity: 150   Type: sanitary   Color:    Expiration Date:\s
                        """;

        assertEquals(expectedOutput.replaceAll("\\r\\n", "\n").trim(), outContent.toString().replaceAll("\\r\\n", "\n").trim());
    }

    @Test
    void testPrintProductsSortedByPrice() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        System.setOut(new PrintStream(outContent));

        Product.printProductsSortedByPrice();

        System.setOut(System.out);

        String expectedOutput =
                """
                        Product ID: 1   Product Name: oranges   Price: 0.1   Quantity: 500   Type: food   Color:    Expiration Date: 06-07-2021
                        Product ID: 2   Product Name: toilet paper   Price: 2.5   Quantity: 150   Type: sanitary   Color:    Expiration Date:\s
                        Product ID: 3   Product Name: lipstick   Price: 6.8   Quantity: 15   Type: makeup   Color: red   Expiration Date: 04-02-2025
                        Product ID: 4   Product Name: dog leash   Price: 10.3   Quantity: 6   Type: others   Color: blue   Expiration Date:\s
                        """;

        assertEquals(expectedOutput.replaceAll("\\r\\n", "\n").trim(), outContent.toString().replaceAll("\\r\\n", "\n").trim());
    }

    @Test
    void testPrintProductsSortedByExpirationDate() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        System.setOut(new PrintStream(outContent));

        Product.printProductsSortedByExpirationDate();

        System.setOut(System.out);

        String expectedOutput =
                """
                        Product ID: 2   Product Name: toilet paper   Price: 2.5   Quantity: 150   Type: sanitary   Color:    Expiration Date:\s
                        Product ID: 4   Product Name: dog leash   Price: 10.3   Quantity: 6   Type: others   Color: blue   Expiration Date:\s
                        Product ID: 3   Product Name: lipstick   Price: 6.8   Quantity: 15   Type: makeup   Color: red   Expiration Date: 04-02-2025
                        Product ID: 1   Product Name: oranges   Price: 0.1   Quantity: 500   Type: food   Color:    Expiration Date: 06-07-2021
                        """;


        assertEquals(expectedOutput.replaceAll("\\r\\n", "\n").trim(), outContent.toString().replaceAll("\\r\\n", "\n").trim());
    }

    @Test
    void testPrintSpecificProduct() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        String input = "lipstick\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        System.setOut(new PrintStream(outContent));

        Product.printSpecificProduct();

        System.setIn(System.in);
        System.setOut(System.out);

        String expectedOutput = "Enter product ID or name: " +
                "Product ID: 3   Product Name: lipstick   Price: 6.8   Quantity: 15   Type: makeup   Color: red   Expiration Date: 04-02-2025\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    void testPrintProductsWithPriceGreaterThanOrEqual() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 10, ProductType.SANITARY, "", "");

        Product.products = new ArrayList<>();
        Product.products.add(product1);
        Product.products.add(product2);

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        System.setOut(new PrintStream(outContent));

        Product.printProductsWithPriceGreaterThanOrEqual();

        String expectedOutput = "Enter minimum price: " +
                "Products with Price Greater Than or Equal To 1.0:" +
                System.lineSeparator() +
                "Product ID: 2   Product Name: toilet paper   Price: 2.5   Quantity: 10   Type: sanitary   Color:    Expiration Date: " +
                System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintProductsWithPriceLessThan() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        // Redirect System.in to provide input for the test
        String input = "5\n"; // Enter the maximum price
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect System.out to capture the printed output
        System.setOut(new PrintStream(outContent));

        // Act
        Product.printProductsWithPriceLessThan();

        // Assert
        String expectedOutput = "Enter maximum price: " +
                "Products with Price Lower Than 5.0:" +
                "Product ID: 1   Product Name: oranges   Price: 0.1   Quantity: 500   Type: food   Color:    Expiration Date: 06-07-2021" +
                "Product ID: 2   Product Name: toilet paper   Price: 2.5   Quantity: 150   Type: sanitary   Color:    Expiration Date: " +
                "";

        assertEquals(expectedOutput.replaceAll("\\R", ""), outContent.toString().replaceAll("\\R", ""));
    }

    @Test
    void testPrintProductsWithQuantityGreaterThanOrEqual() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        System.setOut(new PrintStream(outContent));

        ByteArrayInputStream inContent = new ByteArrayInputStream("10".getBytes());
        System.setIn(inContent);

        try {
            Product.printProductsWithQuantityGreaterThanOrEqual();
        } catch (NoSuchElementException ignored) {
        } finally {
            System.setOut(System.out);
            System.setIn(System.in);
        }

        String expectedOutput = """
                Enter minimum quantity: Product ID: 1   Product Name: oranges   Price: 0.1   Quantity: 500   Type: food   Color:    Expiration Date: 06-07-2021
                Product ID: 2   Product Name: toilet paper   Price: 2.5   Quantity: 150   Type: sanitary   Color:    Expiration Date:\s
                Product ID: 3   Product Name: lipstick   Price: 6.8   Quantity: 15   Type: makeup   Color: red   Expiration Date: 04-02-2025
                """;

        assertEquals(expectedOutput.trim(), outContent.toString().replaceAll("\\r\\n", "\n").trim());
    }

    @Test
    void testPrintProductsWithQuantityLessThan() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        System.setOut(new PrintStream(outContent));

        ByteArrayInputStream inContent = new ByteArrayInputStream("10".getBytes());
        System.setIn(inContent);

        try {
            Product.printProductsWithQuantityLessThan();
        } catch (NoSuchElementException ignored) {
        } finally {
            System.setOut(System.out);
            System.setIn(System.in);
        }

        String expectedOutput = "Enter maximum quantity: " +
                "Product ID: 4   Product Name: dog leash   Price: 10.3   Quantity: 6   Type: others   Color: blue   Expiration Date: \n";

        assertEquals(expectedOutput.trim(), outContent.toString().replaceAll("\\r\\n", "\n").trim());
    }

    @Test
    void testAddProduct() {
        String input = "TestProduct\n10.0\n50\nfood\nRed\n2024-01-01\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        System.setOut(new PrintStream(outContent));

        Product.addProduct();

        System.setIn(System.in);
        System.setOut(System.out);

        String expectedOutput = "Enter product name: Enter product price: Enter product quantity: " +
                "Enter product type (food, drinks, sanitary, makeup, others): Enter product color: Enter product expiration date: " +
                "Product added successfully.\n";

        assertEquals(expectedOutput.trim(), outContent.toString().replaceAll("\\r\\n", "\n").trim());

        assertEquals(1, Product.products.size()); // One product should be added
        Product addedProduct = Product.products.get(0);
        assertEquals("TestProduct", addedProduct.productName);
        assertEquals(10.0, addedProduct.price, 0.001);
        assertEquals(50, addedProduct.quantity);
        assertEquals(ProductType.FOOD, addedProduct.type);
        assertEquals("Red", addedProduct.color);
        assertEquals("2024-01-01", addedProduct.expirationDate);
    }

    @Test
    void testChangeProductPrice() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        String input = "2\n8.0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        System.setOut(new PrintStream(outContent));

        Product.changeProductPrice();

        String expectedOutput = "Enter product ID to change price: Enter new price: Invalid input. Please enter a valid number.";
        assertEquals(expectedOutput, outContent.toString().replace("\r", "").trim());
        assertEquals(2.5, product2.price, 0.001);
        scanner.close();
    }

    @Test
    void testChangeProductQuantity() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        String input = "2\n50\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        System.setOut(new PrintStream(outContent));

        Product.changeProductQuantity();

        int expectedQuantity = 50;

        int actualQuantity = -1;
        for (Product product : Product.products) {
            if (product.productId == 2) {
                actualQuantity = product.quantity;
                break;
            }
        }

        assertEquals(expectedQuantity, actualQuantity);
        scanner.close();
    }

    @Test
    void testChangeProductName() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        String input = "2\ntissue\n"; // Enter product ID to change name and new name
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        System.setOut(new PrintStream(outContent));

        Product.changeProductName();

        String expectedOutput = "Enter product ID to change name: Enter new name: Name changed successfully.";
        String actualOutput = outContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();

        assertEquals(expectedOutput, actualOutput);
        assertEquals("tissue", product2.productName);
    }

    @Test
    void testDeleteProduct() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        String input = "3\n"; // Enter product ID to delete
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        System.setOut(new PrintStream(outContent));

        Product.deleteProduct();

        String expectedOutput = "Enter product ID to delete: Product deleted successfully.";
        String actualOutput = outContent.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();
        assertEquals(expectedOutput, actualOutput);

        Iterator<Product> iterator = Product.products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            assertNotEquals(3, product.productId);
        }
    }

    @Test
    void testSaveProductsToFile() throws IOException {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 150, ProductType.SANITARY, "", "");
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        System.setOut(new PrintStream(outContent));

        Product.saveProductsToFile();

        String expectedOutput = "Products saved to file: ";
        assertTrue(outContent.toString().contains(expectedOutput));

        String filename = outContent.toString().replace(expectedOutput, "").trim();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder fileContents = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.append(line).append("\n");
            }

            String expectedFileContents = "id,name,price,quantity,type,color,expires_in\n" +
                    "1,oranges,0,10,500,food,,06-07-2021\n" +
                    "2,toilet paper,2,50,150,sanitary,,\n" +
                    "3,lipstick,6,80,15,makeup,red,04-02-2025\n" +
                    "4,dog leash,10,30,6,others,blue,\n";
            assertEquals(expectedFileContents, fileContents.toString());
        }
    }

    @Test
    void testPrintAvailableProducts() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 0, ProductType.SANITARY, "", ""); // Quantity is 0
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);


        System.setOut(new PrintStream(outContent));

        Customer.printAvailableProducts();

        String expectedOutput = "Available Products:\n" +
                "Product ID: 1   Product Name: oranges   Price: 0.1   Quantity: 500   Type: food   Color:    Expiration Date: 06-07-2021\n" +
                "Product ID: 3   Product Name: lipstick   Price: 6.8   Quantity: 15   Type: makeup   Color: red   Expiration Date: 04-02-2025\n" +
                "Product ID: 4   Product Name: dog leash   Price: 10.3   Quantity: 6   Type: others   Color: blue   Expiration Date: \n";
        assertEquals(expectedOutput, outContent.toString().replace("\r", ""));
    }

    @Test
    void testSearchProductByCategory() {
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 0, ProductType.SANITARY, "", ""); // Quantity is 0
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        System.setOut(new PrintStream(outContent));

        String input = "food\n"; // Enter the category to search
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Customer.searchProductByCategory();

        System.setIn(originalIn);

        String expectedOutput = "Enter product category to search: Search Results:" +
                System.lineSeparator() +
                "Product ID: 1   Product Name: oranges   Price: 0.1   Quantity: 500   Type: food   Color:    Expiration Date: 06-07-2021" +
                System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }       //UNFINISHED

    @Test
    void testSearchProductByName() {
        // Arrange
        Product product1 = new Product(1, "oranges", 0.1, 500, ProductType.FOOD, "", "06-07-2021");
        Product product2 = new Product(2, "toilet paper", 2.5, 0, ProductType.SANITARY, "", ""); // Quantity is 0
        Product product3 = new Product(3, "lipstick", 6.8, 15, ProductType.MAKEUP, "red", "04-02-2025");
        Product product4 = new Product(4, "dog leash", 10.3, 6, ProductType.OTHERS, "blue", "");

        Product.products.add(product1);
        Product.products.add(product2);
        Product.products.add(product3);
        Product.products.add(product4);

        // Redirect System.in to provide input for the test
        String input = "oranges\n"; // Enter the name to search
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect System.out to capture the printed output
        System.setOut(new PrintStream(outContent));

        // Act
        Customer.searchProductByName();

        // Assert
        String expectedOutput = "Enter product name to search: Search Results:" +
                System.lineSeparator() +
                "Product ID: 1   Product Name: oranges   Price: 0.1   Quantity: 500   Type: food   Color:    Expiration Date: 06-07-2021" +
                System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}

