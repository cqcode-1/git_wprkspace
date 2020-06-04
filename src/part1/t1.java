package part1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class t1 {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
//        String format = dateFormat.format(new Date("13235708621236538"));
//        long parse = Date.parse("13235708621236538");

        System.out.println("13235708621236538");
        System.out.println(System.currentTimeMillis());
    }
}

