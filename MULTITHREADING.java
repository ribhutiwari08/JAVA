import java.io.*;
import java.util.*;
import java.util.concurrent.*;
public class question3 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Shared resources
        List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());
        File inputFile = new File("input_numbers.txt");
        File outputFile = new File("output_numbers.txt");

        // Task 1: Create a file and write 100 random integers
        Runnable writeTask = () -> {
            try (FileWriter writer = new FileWriter(inputFile)) {
                Random random = new Random();
                for (int i = 0; i < 100; i++) {
                    writer.write(random.nextInt(100) + 1 + "\n");
                }
                System.out.println("File created with 100 random integers.");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        };

        // Task 2: Read integers from the file
        Runnable readTask = () -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    numbers.add(Integer.parseInt(line));
                }
                System.out.println("File read successfully.");
            } catch (IOException e) {
                System.err.println("Error reading from file: " + e.getMessage());
            }
        };

        // Task 3: Sort the integers
        Runnable sortTask = () -> {
            synchronized (numbers) {
                Collections.sort(numbers);
                System.out.println("Integers sorted.");
            }
        };

        // Task 4: Calculate sum and product
        Runnable calculateTask = () -> {
            synchronized (numbers) {
                long sum = numbers.stream().mapToLong(Integer::longValue).sum();
                long product = 1;
                for (int num : numbers) {
                    product *= num;
                    product %= (long) 1e9 + 7; // Prevent overflow
                }
                // Write results back to a shared structure
                numbers.add((int) sum); // Using numbers list to hold results
                numbers.add((int) product); // Add results at the end
                System.out.println("Sum and product calculated.");
            }
        };

        // Task 5: Write the sorted integers, sum, and product to another file
        Runnable writeOutputTask = () -> {
            try (FileWriter writer = new FileWriter(outputFile)) {
                synchronized (numbers) {
                    writer.write("Sorted List of Integers:\n");
                    for (int i = 0; i < numbers.size() - 2; i++) { // Last two are sum & product
                        writer.write(numbers.get(i) + "\n");
                    }
                    writer.write("\nSum: " + numbers.get(numbers.size() - 2) + "\n");
                    writer.write("Product (mod 1e9+7): " + numbers.get(numbers.size() - 1) + "\n");
                }
                System.out.println("Results written to output file.");
            } catch (IOException e) {
                System.err.println("Error writing to output file: " + e.getMessage());
            }
        };

        // Submit tasks to the executor
        executor.submit(writeTask);
        executor.submit(readTask);
        executor.submit(sortTask);
        executor.submit(calculateTask);
        executor.submit(writeOutputTask);

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("Task execution interrupted: " + e.getMessage());
        }
    }
}
