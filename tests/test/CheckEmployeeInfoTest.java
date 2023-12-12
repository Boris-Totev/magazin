import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckEmployeeInfoTest {

    @Test
    void testCheckEmployeeCredentials() {
        // GIVEN
        Employee.employees.clear();
        Employee employee1 = new Employee(1, "Viki", "Veleva", 23, 400);
        Employee employee2 = new Employee(2, "Michel", "Gibson", 30, 500);
        Employee.employees.add(employee1);
        Employee.employees.add(employee2);

        // WHEN
        assertTrue(CheckEmployeeInfo.checkEmployeeCredentials(1, "Viki"));
        assertFalse(CheckEmployeeInfo.checkEmployeeCredentials(1, "Niki"));
        assertFalse(CheckEmployeeInfo.checkEmployeeCredentials(3, "Jack"));
    }
}
