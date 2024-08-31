import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // Create a scanner object for input
        System.out.print("Enter the equation into the calculator: ");
        String equation = scanner.nextLine();
 
 
        try {
            Number result = Calculator.eval(equation);  // solve the equation using the Calculator class
            System.out.println("Result: " + result); // give the result
 
 
        } catch (Exception e) { // check for exceptions and returns them
            System.out.println("Error: " + e.getMessage());
 
 
        } scanner.close();
 
 
        }
    }
 
 
 
 
 
 