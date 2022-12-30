package observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UndoableStringBuilderTest {
    GroupAdmin admin;
    UndoableStringBuilder usb;

    @BeforeEach
    void setUp() {
        admin = new GroupAdmin();
        usb = admin.getUsb();
    }

    @Test
    public void givenTest(){
        usb.append("to be or not to be");
        assertEquals("to be or not to be", usb.toString());

        usb.replace(3, 5, "eat");
        assertEquals("to eat or not to be", usb.toString());

        usb.replace(17, 19, "eat");
        assertEquals("to eat or not to eat", usb.toString());

        usb.reverse();
        assertEquals("tae ot ton ro tae ot", usb.toString());

        usb.undo();
        assertEquals("to eat or not to eat", usb.toString());

        usb.undo();
        assertEquals("to eat or not to be", usb.toString());

        usb.undo();
        assertEquals("to be or not to be", usb.toString());

        usb.insert(3, "hello");
        assertEquals("to hellobe or not to be", usb.toString());

        usb.delete(3,8);
        assertEquals("to be or not to be", usb.toString());

    }


    @Test
    public void appendExceptionsTest() {

        assertThrows(RuntimeException.class,
                () -> usb.append(null));
    }
    @Test
    public void append(){
        String str = "agfdsgfdg";
        usb.append(str);

        StringBuilder sb = new StringBuilder();
        sb.append(str);


    }

    @Test
    public void deleteUndefinedStringExceptionsTest() {

        assertThrows(RuntimeException.class,
                () -> usb.delete(1,3));
    }
    @Test
    public void undoExceptionsTest() {

        assertThrows(RuntimeException.class,
                usb::undo);
    }

    @Test
    public void deleteStartNegativeExceptionsTest() {

        usb.append("monkeys");
        assertThrows(RuntimeException.class,
                () -> usb.delete(-7,3));
    }


    @Test
    public void deleteStartBiggerThenEndExceptionsTest() {

        usb.append("monkeys");
        assertThrows(RuntimeException.class,
                () -> usb.delete(5,2));
    }

    @Test
    public void deleteEndBiggerThenLengthExceptionsTest() {

        usb.append("monkeys");
        assertThrows(RuntimeException.class,
                () -> usb.delete(5,8));
    }
    @Test
    public void insertOffsetIsLessThenZeroExceptionsTest() {

        assertThrows(RuntimeException.class,
                () -> usb.insert(-5,"monkeys"));
    }

    @Test
    public void insertOffsetBiggerThenLengthExceptionsTest() {

        assertThrows(RuntimeException.class,
                () -> usb.insert(10,"monkeys"));
    }

    @Test
    public void replaceStartNegativeExceptionsTest() {

        usb.append("monkeys");
        assertThrows(RuntimeException.class,
                () -> usb.replace(-7,3, "jumping on the bed"));
    }


    @Test
    public void replaceStartBiggerThenEndExceptionsTest() {

        usb.append("monkeys");
        assertThrows(RuntimeException.class,
                () -> usb.replace(5,2, "jumping on the bed"));
    }

    @Test
    public void replaceEndBiggerThenLengthExceptionsTest() {

        usb.append("monkeys");
        assertThrows(RuntimeException.class,
                () -> usb.replace(5, 8, "jumping on the bed"));
    }

    @Test
    public void reverseExceptionsTest() {

        assertThrows(RuntimeException.class,
                usb::reverse);
    }
}
