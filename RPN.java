package compiladores;

import java.util.Scanner;
import java.util.Stack;

public class RPN {
    private static Scanner scn = new Scanner(System.in);
    private static Stack<Integer> pilha = new Stack<>();

    public static void main(String[] args) {
        int value = 0;
        String input = "";
        System.out.println("Type number or operation (POSTFIX NOTATION) - 'e' to exit");

        while (!input.equals("e")) {
            input = scn.next();

            if (isNumber(input)) {
                pilha.push(Integer.parseInt(input));

            } else if (isOperation(input)) {
                value = initializeOperation(input, pilha);
                pilha.push(value);
            }
        }

        System.out.println(value);
    }

    public static int initializeOperation(String operation, Stack<Integer> pilha) {
        int result;
        if (pilha.empty()) {
            result = 0;
        } else {
            result = pilha.pop();
            result = operate(operation, pilha.pop(), result);
        }
        return result;
    }

    public static int operate(String operation, int left, int right) {
        switch (operation) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/": {
                if (right == 0) {
                    System.out.println("Divisão por zero");
                    return left;
                }
                return left / right;
            }
            default:
                return left;
        }
    }

    public static boolean isOperation(String input) {
        if (input == null) {
            return false;
        } else {
            return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
        }
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}