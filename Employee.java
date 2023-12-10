import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
}

