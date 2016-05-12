import org.junit.Test
import spock.lang.Specification

import static groovy.test.GroovyAssert.shouldFail

/**
 * Created by zxg on 16/4/27.
 */
public class FrequencyTest2 extends Specification {


    def "字符串结果要等于，排序"() {
        expect:
        new Frequency(input).toString() == string;
        where:
        input     || string
        "123"     || "123"
        "7563421" || "1234567"
        "157"     || "157"
    }


    def show_exception_with_str_contain_其他字符() {

        expect:
        shouldFail IllegalArgumentException, {
            new Frequency(input);
        }
        where:
        input   | _
        "12.."  | _
        "123 4" | _
        " 123 " | _

    }


    def 对象相等() {
        expect:
        new Frequency(input) == new Frequency(input2);
        where:
        input     || input2
        "1234"    || "1234"
        "7563421" || "1234567"
        "157"     || "157"

    }


    def 位移() {

        expect:
        new Frequency(main).shift(shift) == new Frequency(result);

        where:
        main      | shift || result
        "123"     | 1     || "234"
        "7563421" | 7     || "1234567"
        "1234"    | 2     || "3456"

    }


    void 包含() {
        expect:
        new Frequency(main).containsAll(new Frequency(sub));

        where:
        main      || sub
        "123"     || "123"
        "7563421" || "1234567"
        "1234"    || "123"
    }

    void 不包含() {
        expect:
        new Frequency(main).containsAll(new Frequency(sub)) == false;

        where:
        main  || sub
        "123" || "1234"
    }
}
