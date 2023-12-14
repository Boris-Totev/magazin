import java.util.Scanner;

public class EnteringMode {
    static void enterEmployeeOrCustomerMode (){
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Select a mode: \n 1. Employee \n 2. Customer");
        int userChoice = inputScanner.nextInt();
        if (userChoice == 1) {

            boolean successfulLogin = false;

            do {
                System.out.print("Enter your id: ");
                int id = inputScanner.nextInt();
                inputScanner.nextLine();

                System.out.print("Enter your name: ");
                String name = inputScanner.nextLine();

                if (CheckEmployeeInfo.checkEmployeeCredentials(id, name)) {
                    System.out.println("Successful login as an employee.");
                    successfulLogin = true;
                } else {
                    System.out.println("Login failed. Please try again later..");
                }
            } while (!successfulLogin);
            System.out.println("You are in employee mode.");
            Employee.showMenu();
        } else if (userChoice == 2) {
            System.out.println("You are in customer mode.");
            Customer.showCustomerMenu();
        } else {
            System.out.println("Invalid selection. The program ends.");
        }
        inputScanner.close();
    }
}

