import org.apache.commons.lang3.StringUtils;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frequency frequency = (Frequency) o;

        return !(field != null ? !new Frequency(field).toString().equals(frequency.toString()) : frequency.field != null);

    }

    @Override
    public int hashCode() {
        return field != null ? field.hashCode() : 0;
    }


    public Frequency shift(int index) {
        String temp = "";
        char arr[] = field.toCharArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int val = Integer.parseInt(arr[i]+"");
            if ((val+index) % 7 == 0) {
                temp += (7 + "");
            } else {
                temp += ((val+index)%7 + "");
            }
        }
        System.out.println("temp:"+temp);
        return new Frequency(temp);
    }

    public boolean containsAll(Frequency frequency) {
        char arr[] = frequency.field.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (!this.field.contains(arr[i]+"")) {
                return false;
            };
        }
        return true;
    }
}
