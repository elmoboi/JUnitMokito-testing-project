import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;


public class HorseTest {

        @Test
    public void nullNameException() {
            assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));
    }
    @Test
    public void nullNameExceptionEqualRightErrorMessage() {
        String expected = "Name cannot be null.";
        String actual = assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1)).getMessage();

        assertEquals(expected,actual);
    }
    @ParameterizedTest
    @ValueSource (strings = {" ","^\\s*$","^\\d*$"})
    public void nullOrSpacedTabException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("  ",1,1));
    }
    @ParameterizedTest
    @ValueSource (strings = {" ","^\\s*$","^\\d*$"})
    public void nullOrSpacedTabExceptionEqualRightErrorMessage() {
        String expected = "Name cannot be blank.";
        String actual = assertThrows(IllegalArgumentException.class, () -> new Horse("  ",1,1)).getMessage();

        assertEquals(expected,actual);
    }

    @Test
    public void negativeSpeedException() {
            assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1,1));
    }
    @Test
    public void negativeSpeedExceptionEqualRightErrorMessage() {
        String actual = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1,1)).getMessage();
        String expected = "Speed cannot be negative.";

        assertEquals(expected,actual);
    }
    @Test
    public void negativeDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1,-1));
    }
    @Test
    public void negativeDistanceExceptionEqualRightErrorMessage() {
        String actual = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1,-1)).getMessage();
        String expected = "Distance cannot be negative.";

        assertEquals(expected,actual);
    }
    @Test
    public void getNameReturnRightValue() {
            String expected = "name";
            String actual = new Horse("name",1,1).getName();

            assertEquals(expected,actual);
    }
    @Test
    public void getSpeedReturnRightValue() {
        Double expected = 1.0;
        Double actual = new Horse("name",1,1).getSpeed();

        assertEquals(expected,actual);
    }
    @Test
    @NullAndEmptySource
    public void getDistanceReturnRightValue() {
        Double expected = 0D;
        Double actual = new Horse("name",1).getDistance();

        assertEquals(expected,actual);
    }
    @Test
    public void moveReturnRightValue() {
        try(MockedStatic<Horse> utils = Mockito.mockStatic(Horse.class)) {
            new Horse("name",1,1).move();
            utils.verify(
                    ()->Horse.getRandomDouble(0.2,0.9)
            );

        }
    }
    @Test
    public void moveReturnDistanceRightValue() {
        try(MockedStatic<Horse> utils = Mockito.mockStatic(Horse.class)) {
            new Horse("name",31,100).move();
            utils.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }
}
