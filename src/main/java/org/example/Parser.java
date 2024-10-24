package org.example;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.example.Utils.OPERATORS;
import static org.example.Utils.isNumber;

public class Parser {

    public static String parseExpression(String inputString) {
        String[] input = inputString.split(" ");
        List<String> output = new ArrayList<>();
        Deque<String> stack = new LinkedList<>();
        for (String cur : input) {
            //Если токен — число, то добавить его в очередь вывода.
            if (isNumber(cur)) {
                output.add(cur);
            }

            //Если токен — оператор op1, то:
            if (OPERATORS.contains(cur)) {
                //Пока присутствует на вершине стека токен оператор op2, чей приоритет выше или равен приоритету op1,
                // и при равенстве приоритетов op1 является левоассоциативным:
                while (!stack.isEmpty() && !stack.peek().equals("(") && priority(stack.peek()) >= priority(cur)) {
                    //Переложить op2 из стека в выходную очередь;
                    output.add(stack.pop().toString());
                }
                //Положить op1 в стек.
                stack.push(cur);
            }
            if ((cur.equals("(") || cur.equals(")")) && stack.peek() != null
                    && (stack.peek().equals("sin") || stack.peek().equals("cos")
                    || stack.peek().equals("sqr") || stack.peek().equals("pow"))) {
                continue;
            }
            //Если токен — открывающая скобка, то положить его в стек.
            if (cur.equals("(")) {
                stack.push(cur);
            }
            //Если токен — закрывающая скобка:
            if (cur.equals(")")) {
                //Пока токен на вершине стека не открывающая скобка
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    //Переложить оператор из стека в выходную очередь.
                    output.add(stack.pop().toString());
                }
                //Если стек закончился до того, как был встречен токен открывающая скобка, то в выражении пропущена скобка.
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Missing '(' in expression");
                }
                //Выкинуть открывающую скобку из стека, но не добавлять в очередь вывода.
                stack.pop();
            }
        }
        //Если больше не осталось токенов на входе:
        //Пока есть токены операторы в стеке:
        while (!stack.isEmpty()) {
            //Если токен оператор на вершине стека — открывающая скобка, то в выражении пропущена скобка.
            if (stack.peek().equals("(")) {
                throw new IllegalArgumentException("Missing ')' in expression");
            }
            //Переложить оператор из стека в выходную очередь.
            output.add(stack.pop().toString());
        }


        return String.join(" ", output);
    }

    private static int priority(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "pow":
                return 3;
            case "sqr":
            case "sin":
            case "cos":
                return 4;
            default:
                throw new IllegalArgumentException("Unknown operator");
        }
    }
}
