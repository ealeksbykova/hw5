package org.example;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.example.Utils.OPERATORS;
import static org.example.Utils.isNumber;

public class Executor {
    public static Double evaluateExpression(String parsedExpression) {
        String[] expressionTokens = parsedExpression.split(" ");
        Deque<Double> values = new LinkedList<>();
        for (String token : expressionTokens) {
            if (isNumber(token)) {
                values.push(Double.parseDouble(token));
                continue;
            }

            try {
                if (OPERATORS.contains(token)) {
                    double result = 0d;

                    switch (token) {
                        case "+": {
                            double operandA = values.pop();
                            double operandB = values.pop();
                            result = operandA + operandB;
                        }
                        break;
                        case "-": {
                            double operandA = values.pop();
                            double operandB = values.pop();
                            result = operandB - operandA;
                        }
                        break;
                        case "*": {
                            double operandA = values.pop();
                            double operandB = values.pop();
                            result = operandA * operandB;
                        }
                        break;
                        case "/": {
                            double operandA = values.pop();
                            double operandB = values.pop();
                            result = operandB / operandA;
                        }
                        break;
                        case "sqr": {
                            double operand = values.pop();
                            result = Math.sqrt(operand);
                        }
                        break;
                        case "cos": {
                            double operand = values.pop();
                            result = Math.cos(operand);
                        }
                        break;
                        case "sin": {
                            double operand = values.pop();
                            result = Math.sin(operand);
                        }
                        break;
                        case "pow": {
                            double operandA = values.pop();
                            double operandB = values.pop();
                            result = Math.pow(operandB, operandA);
                        }
                        break;
                    }

                    values.push(result);
                }
            } catch (NoSuchElementException e) {
                System.err.println("No such token: " + token);
            }
        }
        return values.pop();
    }
}
