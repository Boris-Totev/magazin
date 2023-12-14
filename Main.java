import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ReadCsvFiles.readingFiles();
        EnteringMode.enterEmployeeOrCustomerMode();
    }
}

