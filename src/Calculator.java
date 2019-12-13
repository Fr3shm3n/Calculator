import java.util.Stack;
import java.util.Scanner;

import static java.lang.System.exit;

public class Calculator {
    private int pCounter = 0;
    private double num1, num2;
    public static String errMsg = "Error occurred.";

    // Function to perform addition operation
    public double add(double x, double y)
    {
        this.num1 = x;
        this.num2 = y;
        return (num1 + num2);
    }

    // Function to perform subtraction operation
    public double sub(double x, double y)
    {
        this.num1 = x;
        this.num2 = y;
        return (num1 - num2);
    }

    // Function to perform multiplication operation
    public double mult(double x, double y)
    {
        this.num1 = x;
        this.num2 = y;
        return (num1 * num2);
    }

    //Function to perform  division operation
    public double div(double x, double y)
    {
        this.num1 = x;
        this.num2 = y;
        return (num1 / num2);
    }

    // Function to perform modulo operation
    public double mod(double x, double y)
    {
        this.num1 = x;
        this.num2 = y;
        return (num1 % num2);
    }

    // Function to check whether user input a valid symbol
    // Also makes sure there are no open parentheses
    public boolean symCheck(String sym)
    {
        switch(sym)
        {
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            {
                return true;
            }
            case "(": {
                pCounter++;
                return true;
            }
            case ")": {
                pCounter--;
                return true;
            }
            case "q": {
                System.out.println("Terminating application");
                exit(0);
            }
            default:
                return false;
        }
    }

    // Function to decide which operation to perform and returns result
    public Double operation(String operator, Double x, Double y)
    {
        switch(operator)
        {
            case "+":
                this.num1 = add(x, y);
                break;
            case "-":
                this.num1 = sub(x, y);
                break;
            case "*":
                this.num1 = mult(x, y);
                break;
            case "/":
                this.num1 = div(x, y);
                break;
            case "%":
                this.num1 = mod(x, y);
                break;
            default:
                break;
        }
        return num1;
    }

    public static void main(String[] args)
    {
        Calculator cal = new Calculator();
        Double x, y;
        String temp;
        Boolean math = true;
        Stack<Double> operands = new Stack<Double>();
        Stack<String> operators = new Stack<String>();
        while(true)
        {
            operands.clear();
            operators.clear();
            System.out.print("Enter your equation here: ");
            Scanner in = new Scanner(System.in);

            while(in.hasNext()) {
                // If there are 2 or more operands and 1 or more operators, perform the mathematical function
                // And print out the result
                if (operands.size() >= 2 && operands.size() >= 1) {
                    y = operands.pop();
                    x = operands.pop();
                    operands.push(cal.operation(operators.pop(), x, y));
                }
                else {
                    // If the next token is a double, push it into the operands stack
                    if (in.hasNextDouble()) {
                        operands.push(in.nextDouble());
                    }
                    // If next token is a symbol, check if it is valid. If it is, push to operators stack.
                    // If it is not return error.
                    else {
                        temp = in.next();
                        if (cal.symCheck(temp) == true && !temp.equals(";") && !temp.equals(")")) {
                            operators.push(temp);
                        }
                        else if(temp.equals(")"))
                        {
                            operators.pop();
                        }
                        else if(temp.equals(";"))
                        {
                            break;
                        }
                        else {
                            System.out.println("There is an invalid symbol in your equation. Please try again.");
                            break;
                        }
                    }
                }
            }

            // If there are more operators then operands, returns an error
            if (operators.size() > operands.size())
                System.out.println("There are not enough operands in your equation.");
            // Check to see if the number of left and right parentheses are equal to each other
            // If they are not, return error
            else if(cal.pCounter != 0)
                System.out.println("There is an error in your equation, too many/few parentheses. Please try again.");
            else
                System.out.println(operands.pop());
        }
    }
}
