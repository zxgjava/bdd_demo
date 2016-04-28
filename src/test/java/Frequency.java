import java.util.Arrays;

/**
 * Created by zxg on 16/4/27.
 */
public class Frequency {
    private String field;
    public Frequency(String field) {
        this.field = field;
        char arr[] = field.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if ("1234567".indexOf(Integer.parseInt(arr[i]+"")+"") < 0){
                throw new IllegalArgumentException(arr[i]+"");
            }
        }
    }
    public String toString () {
        String temp = "";
        char arr[] = field.toCharArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            temp += (arr[i] + "");
        }
        return temp;
    }
}
