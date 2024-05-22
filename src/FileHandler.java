package src;

import java.io.File;
import java.util.Scanner;

public class FileHandler {
    public static File validateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File does not exist. Please enter a valid file name: ");
            Scanner scanner = new Scanner(System.in);
            String newFileName = scanner.next();
            return validateFile(newFileName);
        }
        return file;
    }


}
