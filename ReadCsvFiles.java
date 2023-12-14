import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCsvFiles {
    static void readingFiles () throws FileNotFoundException {

        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Welcome to the store program!\nEnter the name of the CSV file for employees: ");
        String fileNameEmployees = inputScanner.nextLine();
        String csvFileEmployees = fileNameEmployees + ".csv";

        try {
            File file = new File(csvFileEmployees);
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] columns = line.split(",");
                int employeeId = Integer.parseInt(columns[0]);
                String firstName = columns[1];
                String lastName = columns[2];
                int age = Integer.parseInt(columns[3]);
                int salary = Integer.parseInt(columns[4]);

                Employee.employees.add(new Employee(employeeId, firstName, lastName, age, salary));
            }

            scanner.close();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.print("Enter the name of the CSV file for products: ");
        String fileNameProducts = inputScanner.nextLine();
        String csvFileProducts = fileNameProducts + ".csv";

        try {
            File fileProducts = new File(csvFileProducts);
            Scanner scanner = new Scanner(fileProducts);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] columns = line.split(",");
                int productId = Integer.parseInt(columns[0]);
                String productName = columns[1];
                double price = Double.parseDouble(columns[2]);
                int quantity = Integer.parseInt(columns[3]);
                ProductType type = ProductType.valueOf(columns[4]);
                String color = "";
                if (columns.length > 5 && !columns[5].isEmpty()) {
                    color = columns[5];
                }
                String expirationDate = "";

                if (columns.length > 6 && !columns[6].isEmpty()) {
                    expirationDate = columns[6];
                }

                Product.products.add(new Product(productId, productName, price, quantity, type, color, expirationDate));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
