import java.util.Stack;
public class reverseStrinf {
    
    public static void main(String[] args) {
        String input = "HelloWorld";
        System.out.println("Original String: " + input);

        Stack<Character> stack = new Stack<>();

        // Push characters to stack
        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        // Pop characters from stack to form reversed string
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }

        System.out.println("Reversed String: " + reversed);
    }
}


