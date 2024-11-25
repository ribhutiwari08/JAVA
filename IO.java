import java.io.*;
import java.util.*;
public class question2 {
    public static void main(String[] args) {
		try {
            // Step 1: Create a file and write 100 random integers to it
            File inputFile = new File("input_numbers.txt");
            FileWriter writer = new FileWriter(inputFile);
            Random random = new Random();

            for (int i = 0; i < 100; i++) {
                writer.write(random.nextInt(100) + 1 + "\n"); // Random integers between 1 and 100
            }
            writer.close();
            System.out.println("File created with 100 random integers.");

            // Step 2: Read integers from the file
            List<Integer> numbers = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
            reader.close();

            // Step 3: Sort the integers
            Collections.sort(numbers);

            // Step 4: Calculate the sum of integers
            long sum = numbers.stream().mapToLong(Integer::longValue).sum();

            // Step 5: Calculate the product of integers (note: this may overflow)
            long product = 1;
            for (int num : numbers) {
                product *= num; 
                product %= (long) 1e9 + 7; // To prevent overflow, use modulo
            }

            // Step 6: Write sorted list, sum, and product to another file
            File outputFile = new File("output_numbers.txt");
            FileWriter outputWriter = new FileWriter(outputFile);

            outputWriter.write("Sorted List of Integers:\n");
            for (int num : numbers) {
                outputWriter.write(num + "\n");
            }
            outputWriter.write("\nSum of Integers: " + sum + "\n");
            outputWriter.write("Product of Integers (mod 1e9+7): " + product + "\n");

            outputWriter.close();
            System.out.println("Processed data written to 'output_numbers.txt'.");

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

	}
}
