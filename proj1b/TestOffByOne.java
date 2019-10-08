import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars(){
        char a = 'x';
        char b = 'y';
        boolean test = offByOne.equalChars(a , b);
        assertEquals(false, test);
        assertFalse(test);
    }
}