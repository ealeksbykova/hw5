package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    @DisplayName("Успешное вычисление выражения")
    public void calculateSuccessTest() {
        assertEquals(14.0d, homeWork.calculate("2 + 3 * 4"), 0.0001);
        assertEquals(20.0d, homeWork.calculate("( 2 + 3 ) * 4"), 0.0001);

        assertEquals(-1.2401d, homeWork.calculate("4 / 3 * sin ( 130 )"), 0.0001);
        assertEquals(-0.9735d, homeWork.calculate("4 / 3 * cos ( 260 )"), 0.0001);

        assertEquals(39.0d, homeWork.calculate("7 * 5 + pow ( 2 , 2 )"), 0.0001);
        assertEquals(8.3333d, homeWork.calculate("10 - 5 / sqr ( 9 )"), 0.0001);
    }

    @Test
    @DisplayName("Не успешное вычисление выражения")
    public void calculateFailTest() {
        assertThrows(IllegalArgumentException.class, () -> homeWork.calculate("( 2 + 3 * 4"));
        assertThrows(IllegalArgumentException.class, () -> homeWork.calculate("( 2 + 3 } * 4"));
        assertThrows(IllegalArgumentException.class, () -> homeWork.calculate("( 2 + 3 )) * 4"));
        assertThrows(IllegalArgumentException.class, () -> homeWork.calculate(" 2 + 3 ) * 4"));
        assertThrows(IllegalArgumentException.class, () -> homeWork.calculate("( 2 + ) ( 3 * 4"));

        assertThrows(NoSuchElementException.class, () -> homeWork.calculate("(2+3)*4"));
        assertThrows(NoSuchElementException.class, () -> homeWork.calculate("(2 +3) *4"));
    }
}