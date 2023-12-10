class CheckEmployeeInfo {
    protected static boolean checkEmployeeCredentials(int id, String name) {

        for (Employee employee : Employee.employees) {
            if (employee.employeeId == id && employee.firstName.equals(name)) {
                return true;
            }
        }
        return false;
    }
}

