package src;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {
    private static int capacity;
    private static int itemCount;
    private static int[] values;
    private static int[] weights;

    private static int maxValue;
    private static int[] bestCombination;
    private static int iterations;

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("Knapsack problem solver: Brute force edition");
        System.out.println("============================================");
        System.out.println();
        System.out.println("Enter the file path: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();
        filePath = FileHandler.validateFile(filePath).getAbsolutePath();

        try {
            readFile(filePath);
            bestCombination = new int[itemCount];
            int[] currentCombination = new int[itemCount];
            solveKnapsack(0, 0, 0, currentCombination);
            printBestItems();
            System.out.println();
            System.out.println("=====================================");
            System.out.print("Final best value: ");
            System.out.println(maxValue);
            System.out.println(Arrays.toString(bestCombination).replace("true", "1").replace("false", "0"));
            System.out.println("Iterations: " + iterations);
            System.out.println("=====================================");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
        }
    }

    private static void readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String[] kn = reader.readLine().split(" ");
        capacity = Integer.parseInt(kn[0]);
        itemCount = Integer.parseInt(kn[1]);

        values = Arrays.stream(reader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        weights = Arrays.stream(reader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();

        if (values.length != itemCount || weights.length != itemCount) {
            throw new IllegalArgumentException("Invalid input file");
        }
    }

    private static void solveKnapsack(int index, int currentWeight, int currentValue, int[] currentCombination) {
        iterations++;
        if (index == itemCount) {
            if (currentWeight <= capacity && currentValue > maxValue) {
                maxValue = currentValue;
                System.arraycopy(currentCombination, 0, bestCombination, 0, itemCount);
                printBestItems();
            }
            return;
        }

        // Nie wybieraj przedmiotu
        currentCombination[index] = 0;
        solveKnapsack(index + 1, currentWeight, currentValue, currentCombination);

        // Wybierz przedmiot
        currentCombination[index] = 1;
        solveKnapsack(index + 1, currentWeight + weights[index], currentValue + values[index], currentCombination);
    }

    public static void printBestItems() {
        System.out.println();
        System.out.println("Currently best combination: " + Arrays.toString(bestCombination).replace("true", "1").replace("false", "0"));
        System.out.println("Currently best value: " + maxValue);
        System.out.println("Current number of iterations: " + iterations);
    }

}

