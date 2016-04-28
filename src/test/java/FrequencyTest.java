import static org.junit.Assert.*;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by zxg on 16/4/27.
 */
public class FrequencyTest {

    @Test
    public void test(){
        assertEquals("1234567", new Frequency("1234567").toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void show_exception_with_str_contain_其他字符() {
        new Frequency("123457..");

        new Frequency("123 457");
    }
    @Test(expected = IllegalArgumentException.class)
    public void show_exception_with_str_contain_其他字符1() {
        new Frequency("123 457");
    }

    @Test
    public void show_string_orderby_asc() {
        assertEquals("1234567", new Frequency("7564312").toString());
        assertEquals("12367", new Frequency("76312").toString());
    }
}
