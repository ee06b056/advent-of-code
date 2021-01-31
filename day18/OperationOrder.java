package day18;

import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OperationOrder {

    static String simpleCal (String number1, String number2, String operator) {
        switch (operator) {
            case "+":
                return String.valueOf(Long.valueOf(number1) + Long.valueOf(number2));
            case "*":
                return String.valueOf(Long.valueOf(number1) * Long.valueOf(number2));
            default:
                throw new RuntimeException();
        }
    }

    static long calculate (String exp) {
        exp = exp.replaceAll("\\(", "\\( ").replaceAll("\\)", " \\)");
        String[] expArr = exp.split("\\s+");
        Deque<String> operandStack = new LinkedList<>();
        Deque<String> operatorStack = new LinkedList<>();
        for (String s : expArr) {
            switch (s) {
                case "(":
                    operandStack.addLast(s);
                    break;
                case ")":
                    String operand1 = operandStack.pollLast();
                    operandStack.pollLast();
                    if (operandStack.size() > 0 && !operandStack.peekLast().equals("(")){
                        String operator = operatorStack.pollLast();
                        String operand2 = operandStack.pollLast();
                        operandStack.addLast(simpleCal(operand1, operand2, operator));
                    } else {
                        operandStack.addLast(operand1);
                    }
                    break;
                case "+":
                    operatorStack.addLast(s);
                    break;
                case "*":
                    operatorStack.addLast(s);
                    break;
                default:
                    if (operandStack.size() > 0 && !operandStack.peekLast().equals("(")) {
                        String operator = operatorStack.pollLast();
                        String operand2 = operandStack.pollLast();
                        operandStack.addLast(simpleCal(s, operand2, operator));
                    } else {
                        operandStack.addLast(s);
                    }
                    break;
            }
        }
        if (operatorStack.size() != 0) {
            String operator = operatorStack.pollLast();
            String operand1 = operandStack.pollLast();
            String operand2 = operandStack.pollLast();
            operandStack.addLast(simpleCal(operand1, operand2, operator));
        }
        return Long.valueOf(operandStack.pollLast());
    }

    static long solution1 (List<String> expressionList) {
        long sum = 0L;
        for (String expression : expressionList) {
            sum += calculate(expression);
        }
        return sum;
    }

    static void calFromStack (Deque<String> operandStack, Deque<String> operatorStack) {
        
    }

    static long calculate2 (String exp) {
        long res = 0L;
        exp = exp.replaceAll("\\(", "\\( ").replaceAll("\\)", " \\)").trim();
        String[] expArr = exp.split("\\s+");
        Deque<String> operatorStack = new LinkedList<>();
        Deque<String> operandStack = new LinkedList<>();
        for (String e : expArr) {
            switch (e) {
                case "(":
                    operandStack.addLast(e);
                    break;
                case ")":

                    break;
                case "+":

                    break;
                case "*":

                    break;
                default:

                    break;
            }
        }
        return res;
    }

    static long solution2 (List<String> expressionList) {
        long sum = 0L;
        for (String expression : expressionList) {
            sum += calculate2(expression);
        }
        return sum;
    }
    public static void main(String[] args) {
        List<String> expressionList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("day18/input.data"));
            while (scanner.hasNextLine()) {
                expressionList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String exp = "9 + ((9 * 3 * 7 + 9 + 5) * 2 * 7 * 7) + 3 * 5";
        // System.out.println(calculate(exp));
        System.out.println(solution1(expressionList));
    }
}
