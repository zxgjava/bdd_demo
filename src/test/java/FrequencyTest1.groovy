import org.junit.Test;

import static org.junit.Assert.*;
import static groovy.test.GroovyAssert.*;
/**
 * Created by zxg on 16/4/27.
 */
public class FrequencyTest1 {

    @Test
    void "字符串结果要等于，排序"(){
        assert new Frequency("1234567").toString() == "1234567"
        assert  new Frequency("7564312").toString() == "1234567"
        assert  new Frequency("76312").toString() == "12367"
    }

    @Test
    public void show_exception_with_str_contain_其他字符() {

        shouldFail IllegalArgumentException ,{
            new Frequency("123457..");
        }

        shouldFail IllegalArgumentException ,{
            new Frequency("123 457");
        }

        shouldFail IllegalArgumentException,{
            new Frequency("123 457");
        }

    }


    @Test
    void 对象相等() {
        assert new Frequency("1234") ==  new Frequency("1234");
        assert new Frequency("1234") == new Frequency("2314") ;
    }


    @Test
    public void 位移() {
        assert new Frequency("1234").shift(1) == new Frequency("2345");
        assert new Frequency("12345") == new Frequency("12347").shift(1)
        assert new Frequency("12") == new Frequency("17").shift(1)
        assert new Frequency("17").shift(1) == new Frequency("71").shift(1)
        assert new Frequency("17").shift(6) == new Frequency("71").shift(6)
        assert new Frequency("17").shift(7) == new Frequency("71").shift(7)
        assert new Frequency("17").shift(6) ==  new Frequency("67")
    }

    @Test
    public void 包含() {
        assert new Frequency("12345").containsAll(new Frequency("23"));
        assert new Frequency("12345").containsAll(new Frequency("236")) == false
    }
}
