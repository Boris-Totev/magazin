import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.*;

public class Product {
    protected int productId;
    protected String productName;
    protected double price;
    protected int quantity;
    protected ProductType type;
    protected String color;
    protected String expirationDate;
    protected static List<Product> products = new ArrayList<>();

    public ProductType getType() {
        return type;
    }

    public Product(int productId, String productName, double price, int quantity, ProductType type, String color, String expirationDate) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.color = color;
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }


}

