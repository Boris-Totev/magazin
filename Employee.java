import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Employee {
    int employeeId;
    String firstName;
    private String last_name;
    private int age;
    protected double salary;
    protected static List<Employee> employees = new ArrayList<>();
    public Employee(int employeeId, String firstName, String last_name, int age, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.last_name = last_name;
        this.age = age;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    static void sortEmployeesByName() {
        Collections.sort(employees, Comparator.comparing(e -> e.firstName));
        System.out.println("Employees sorted by name.");
        for (Employee employee : employees) {
            System.out.println(employee.getEmployeeId() + " " + employee.getFirstName() + " " + employee.getLast_name() + " " + employee.getAge() + " " + employee.getSalary());
        }
    }

    static void sortEmployeesBySalary() {
        Collections.sort(employees, Comparator.comparingInt(e -> (int) e.salary));
        System.out.println("Employees sorted by salary.");
        for (Employee employee : employees) {
            System.out.println(employee.getEmployeeId() + " " + employee.getFirstName() + " " + employee.getLast_name() + " " + employee.getAge() + " " + employee.getSalary());
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
    protected static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:  \n" +
                    "1. Print all products                                     10. Add a new product \n" +
                    "2. Print products sorted by name                          11. Change product price (by ID) \n" +
                    "3. Print products sorted by price                         12. Change product quantity (by ID) \n" +
                    "4. Print products sorted by expiration date               13. Change product name (by ID) \n" +
                    "5. Print a specific product                               14. Delete a product (by ID) \n" +
                    "6. Print products with price greater than or equal to     15. Sort employees by name \n" +
                    "7. Print products with price less than                    16. Sort employees by salary \n" +
                    "8. Print products with quantity greater than or equal to  17. Save and exit \n" +
                    "9. Print products with quantity less than                 18. Exit without saving");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printAllProducts();
                    break;
                case 2:
                    printProductsSortedByName();
                    break;
                case 3:
                    printProductsSortedByPrice();
                    break;
                case 4:
                    printProductsSortedByExpirationDate();
                    break;
                case 5:
                    printSpecificProduct();
                    break;
                case 6:
                    printProductsWithPriceGreaterThanOrEqual();
                    break;
                case 7:
                    printProductsWithPriceLessThan();
                    break;
                case 8:
                    printProductsWithQuantityGreaterThanOrEqual();
                    break;
                case 9:
                    printProductsWithQuantityLessThan();
                    break;
                case 10:
                    addProduct();
                    break;
                case 11:
                    changeProductPrice();
                    break;
                case 12:
                    changeProductQuantity();
                    break;
                case 13:
                    changeProductName();
                    break;
                case 14:
                    deleteProduct();
                    break;
                case 15:
                    Employee.sortEmployeesByName();
                    break;
                case 16:
                    Employee.sortEmployeesBySalary();
                    break;
                case 17:
                    saveProductsToFile();
                    System.out.println("Changes saved to file. Exiting the system. Goodbye!");
                    System.exit(0);
                case 18:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    static void printAllProducts() {
        for (Product product : Product.products) {
            printProductDetails(product);
        }
    }

    static void printProductsSortedByName() {
        List<Product> sortedProducts = new ArrayList<>(Product.products);
        Collections.sort(sortedProducts, Comparator.comparing(p -> p.productName));
        for (Product product : sortedProducts) {
            printProductDetails(product);
        }
    }

    static void printProductsSortedByPrice() {
        List<Product> sortedProducts = new ArrayList<>(Product.products);
        Collections.sort(sortedProducts, Comparator.comparingDouble(p -> p.price));
        for (Product product : sortedProducts) {
            printProductDetails(product);
        }
    }

    static void printProductsSortedByExpirationDate() {
        List<Product> sortedProducts = new ArrayList<>(Product.products);
        Collections.sort(sortedProducts, Comparator.comparing(p -> p.expirationDate));
        for (Product product : sortedProducts) {
            printProductDetails(product);
        }
    }

    static void printSpecificProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID or name: ");
        String query = scanner.next();

        for (Product product : Product.products) {
            if (String.valueOf(product.productId).equals(query) || product.productName.equals(query)) {
                printProductDetails(product);
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void printProductDetails(Product product) {
        System.out.println();
        System.out.print("Product ID: " + product.productId);
        System.out.print("   Product Name: " + product.productName);
        System.out.print("   Price: " + product.price);
        System.out.print("   Quantity: " + product.quantity);
        System.out.print("   Type: " + product.type);
        System.out.print("   Color: " + product.color);
        System.out.print("   Expiration Date: " + product.expirationDate);
    }

    static void printProductsWithPriceGreaterThanOrEqual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();

        for (Product product : Product.products) {
            if (product.price >= minPrice) {
                printProductDetails(product);
            }
        }
    }

    static void printProductsWithPriceLessThan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        for (Product product : Product.products) {
            if (product.price < maxPrice) {
                printProductDetails(product);
            }
        }
    }

    static void printProductsWithQuantityGreaterThanOrEqual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum quantity: ");
        int minQuantity = scanner.nextInt();

        for (Product product : Product.products) {
            if (product.quantity >= minQuantity) {
                printProductDetails(product);
            }
        }
    }

    static void printProductsWithQuantityLessThan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maximum quantity: ");
        int maxQuantity = scanner.nextInt();

        for (Product product : Product.products) {
            if (product.quantity < maxQuantity) {
                printProductDetails(product);
            }
        }
    }

    static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        ProductType type = null;
        while (type == null) {
            System.out.print("Enter product type (food, drinks, sanitary, makeup, others): ");
            String typeInput = scanner.next();
            try {
                type = ProductType.valueOf(typeInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid product type. Please enter a valid product type.");
            }
        }
        System.out.print("Enter product color: ");
        String color = scanner.nextLine();
        System.out.print("Enter product expiration date: ");
        String expirationDate = scanner.nextLine();

        int newProductId = Product.products.size() + 1;
        Product newProduct = new Product(newProductId, name, price, quantity, type, color, expirationDate);
        Product.products.add(newProduct);

        System.out.println("Product added successfully.");
    }

    static void changeProductPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to change price: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();

        for (Product product : Product.products) {
            if (product.productId == productId) {
                product.price = newPrice;
                System.out.println("Price changed successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void changeProductQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to change quantity: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();

        for (Product product : Product.products) {
            if (product.productId == productId) {
                product.quantity = newQuantity;
                System.out.println("Quantity changed successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void changeProductName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to change name: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new name: ");
        String newName = scanner.next();

        for (Product product : Product.products) {
            if (product.productId == productId) {
                product.productName = newName;
                System.out.println("Name changed successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();

        for (Product product : Product.products) {
            if (product.productId == productId) {
                Product.products.remove(product);
                System.out.println("Product deleted successfully.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    static void saveProductsToFile() {
        try {
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String filename = timestamp.format(formatter) + ".csv";
            FileWriter writer = new FileWriter(filename);
            writer.write("id,name,price,quantity,type,color,expires_in\n");
            Locale.setDefault(Locale.US);
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            for (Product product : Product.products) {
                writer.write(String.format("%d,%s,%s,%d,%s,%s,%s\n",
                        product.productId, product.productName, decimalFormat.format(product.getPrice()), product.quantity, product.type, product.color, product.expirationDate));
            }

            writer.close();

            System.out.println("Products saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }
}

