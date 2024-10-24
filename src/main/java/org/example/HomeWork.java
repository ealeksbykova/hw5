package org.example;

import org.junit.Test;

import static org.example.Executor.evaluateExpression;
import static org.example.ParenthesesChecker.check;
import static org.example.Parser.parseExpression;
import static org.junit.Assert.assertEquals;

public class HomeWork {


    /**
     * <h1>Задание 1.</h1>
     * Требуется реализовать метод, который по входной строке будет вычислять математические выражения.
     * <br/>
     * Операции: +, -, *, / <br/>
     * Функции: sin, cos, sqr, pow <br/>
     * Разделители аргументов в функции: , <br/>
     * Поддержка скобок () для описания аргументов и для группировки операций <br/>
     * Пробел - разделитель токенов, пример валидной строки: "1 + 2 * ( 3 - 4 )" с результатом -1.0 <br/>
     * <br/>
     * sqr(x) = x^2 <br/>
     * pow(x,y) = x^y
     */
    double calculate(String expr) {
        if (!check(expr)) {
            throw new IllegalArgumentException("Invalid expression: " + expr);
        }

        return evaluateExpression(parseExpression(expr));
    }
}
