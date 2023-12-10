import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class EmployeeTest {
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
    void testSortEmployeesByName() {
        Employee employee1 = new Employee(1, "John", "Doe", 30, 50000);
        Employee employee2 = new Employee(2, "Alice", "Smith", 25, 60000);
        Employee employee3 = new Employee(3, "Bob", "Johnson", 35, 55000);

        Employee.employees = new ArrayList<>();
        Employee.employees.add(employee1);
        Employee.employees.add(employee2);
        Employee.employees.add(employee3);

        System.setOut(new PrintStream(outContent));

        Employee.sortEmployeesByName();

        String expectedOutput = "Employees sorted by name." +
                System.lineSeparator() +
                "2 Alice Smith 25 60000.0" +
                System.lineSeparator() +
                "3 Bob Johnson 35 55000.0" +
                System.lineSeparator() +
                "1 John Doe 30 50000.0" +
                System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testSortEmployeesBySalary() {
        Employee employee1 = new Employee(1, "John", "Doe", 30, 50000);
        Employee employee2 = new Employee(2, "Alice", "Smith", 25, 60000);
        Employee employee3 = new Employee(3, "Bob", "Johnson", 35, 55000);

        Employee.employees = new ArrayList<>();
        Employee.employees.add(employee1);
        Employee.employees.add(employee2);
        Employee.employees.add(employee3);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Employee.sortEmployeesBySalary();

        String expectedOutput = "Employees sorted by salary." +
                System.lineSeparator() +
                "1 John Doe 30 50000.0" +
                System.lineSeparator() +
                "3 Bob Johnson 35 55000.0" +
                System.lineSeparator() +
                "2 Alice Smith 25 60000.0" +
                System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}

