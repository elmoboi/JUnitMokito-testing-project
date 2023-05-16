import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    public void ConstructorNullTest() {
        assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));
        String expected = "Horses cannot be null.";
        String actual = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null)).getMessage();
        assertEquals(expected, actual);
        assertThrows(IllegalArgumentException.class, ()->new Hippodrome(Collections.emptyList()));
        String expected2 = "Horses cannot be empty.";
        String actual2 = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(Collections.emptyList())).getMessage();
        System.out.println(actual2);
        assertEquals(expected2, actual2);
    }
    @Test
    public void getHorsesTest() {
        List horseList = Mockito.mock(List.class);
            horseList.add(30,new Horse("name", 10));
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(hippodrome.getHorses(),horseList);
    }
    @Test
    public void moveTest() {
            List<Horse> horseList = new ArrayList<>();
            for(int i=0; i<50; i++) {
                horseList.add(mock(Horse.class));
            }
            new Hippodrome(horseList).move();
            for(Horse horse : horseList) {
                verify(horse).move();
            }
    }
    @Test
    public void getWinnerTest() {
       Horse horse1 = new Horse("qwe",1,1);
       Horse horse2 = new Horse("qwe",1,3);
       Horse horse3 = new Horse("qwe",1,2.9999);
       Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3));

       assertSame(horse2,hippodrome.getWinner());
    }
}
