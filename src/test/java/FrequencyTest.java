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

    @Test
    public void should_equals_with_two_object() {
        assertEquals(new Frequency("1234"), new Frequency("1234"));
    }

    @Test
    public void should_equals_with_two_object_01() {
        assertEquals(new Frequency("1234"), new Frequency("2314"));
    }

    @Test
    public void f12_shift1_f23() {
        assertEquals(new Frequency("1234").shift(1), new Frequency("2345"));
    }

    @Test
    public void f17_shift1_f21() {
        assertEquals(new Frequency("12345"), new Frequency("12347").shift(1));
        assertEquals(new Frequency("12"), new Frequency("17").shift(1));
        assertEquals(new Frequency("17").shift(1).toString(), new Frequency("71").shift(1).toString());
        assertEquals(new Frequency("17").shift(6).toString(), new Frequency("71").shift(6).toString());
        assertEquals(new Frequency("17").shift(7).toString(), new Frequency("71").shift(7).toString());
        assertEquals(new Frequency("17").shift(6), new Frequency("67"));
    }

    @Test
    public void should_result_true() {
        assertTrue(new Frequency("12345").containsAll(new Frequency("23")));
        assertFalse(new Frequency("12345").containsAll(new Frequency("236")));
    }
}
