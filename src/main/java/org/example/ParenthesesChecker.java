package org.example;

import java.util.List;

import static java.util.Arrays.asList;

public class ParenthesesChecker {

    final static List<Character> OPEN = asList('[','(', '{', '<');
    final static  List<Character> CLOSE = asList(']',')', '}', '>');

    /**
     * Проверка открытия и закрытия скобок внутри строки.
     * Каждая открытая скобка [,(,{,< должна иметь соответствующую скобку.
     * Корректные сочетания () (()) (() ())
     * Некорректные сочетания )( (}

     * @param expression
     * @return
     */
    public static boolean check(String expression) {
        MyStack<Character> expectedCloseToken = new LinkedStack<>();
        for (char c : expression.toCharArray()) {
            int pos;
            if((pos = OPEN.indexOf(c)) > -1){
                expectedCloseToken.push(CLOSE.get(pos));
                continue;
            }
            if(CLOSE.indexOf(c) > -1){
                Character expected = expectedCloseToken.pop();
                if( expected == null || c != expected){
                    return false;
                }
            }
        }
        return expectedCloseToken.pop() == null;
    }
}
