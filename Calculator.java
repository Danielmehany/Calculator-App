import java.util.ArrayList;


class Calculator {
   static boolean hasDoubleValue = false; // No decimal


  
   static Number eval(String equation) { // Evaluates the expression as a string
       ArrayList<Double> numbers = new ArrayList<>();  // Stores numbers
       ArrayList<Character> operators = new ArrayList<>();  // Stores operators
       String currentNum = "";
       equation = equation.replaceAll(" ", ""); // removes space


       for (int i = 0; i < equation.length(); i++) { // repeat over each character in the equation
           char currentCharacter = equation.charAt(i); // iterate through each character in the equation and sets it equal to currentCharacter


         
           if (Character.isDigit(currentCharacter) || currentCharacter == '.') {
               currentNum += currentCharacter; // If current character is a digit or a decimal point, add to currentNum
              
               if (currentCharacter == '.') { // If decimal point is found, set hasDoubleValue to true
                   hasDoubleValue = true;
               }
           } else {
               // When a non-digit character is encountered, breakdown currentNum as a double
               // and add to the numbers list, then reset currentNum for next number
               if (!currentNum.isEmpty()) {
                   numbers.add(Double.parseDouble(currentNum));
                   currentNum = "";
               }
             
               while (!operators.isEmpty() && operationOrders(currentCharacter) <= operationOrders(operators.get(operators.size() - 1))) {
                   usingOperator(numbers, operators);
               }
             
               operators.add(currentCharacter); // Add operator
           }
       }


       if (!currentNum.isEmpty()) { // Add leftovers to list
           numbers.add(Double.parseDouble(currentNum));
       }


       while (!operators.isEmpty()) {   // Apply all remaining operators
           usingOperator(numbers, operators);
       }


       double result = numbers.get(0);
       return hasDoubleValue ? result : (int) result; // Return the result as a double or int
   }


 
   static int operationOrders(char operator) {  // Determines the priority order of operators for the calculation
       if (operator == '+' || operator == '-') {
           return 1;
       } else if (operator == '*' || operator == '/') {
           return 2;
       } else if (operator == '^' || operator == 'r') {
           return 3;
       }
       return 0;
   }


   static void usingOperator(ArrayList<Double> numbers, ArrayList<Character> operators) {  // Applies an operator to the two most recent numbers in the numbers list
       if (operators.isEmpty()) {
           return; // quit if no operators to apply
       }
      
       char operator = operators.remove(operators.size() - 1); // Remove and get the last operator from the list
       double b = numbers.remove(numbers.size() - 1); // Remove and get the last two numbers from the list
       double a = numbers.size() > 0 ? numbers.remove(numbers.size() - 1) : 0;


       double result = 0; // intiat result


       switch (operator) {  // Apply the operator to a and b, and store the result


           case '+':
               result = a + b;
               break;


           case '-':
               result = a - b;
               break;


           case '*':
               result = a * b;
               break;


           case '/':
               result = a / b;
               break;


           case '^':
               result = Math.pow(a, b); // Exponent
               break;


           case 'r':
               result = Math.pow(b, 0.5); // Square root using exponent of 0.5
               break;
       }


      
       numbers.add(result); // Adding the result back to the numbers list
   }
}


