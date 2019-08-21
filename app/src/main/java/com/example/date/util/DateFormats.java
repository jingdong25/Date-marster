package com.example.date.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormats {

    private String sum;

    public String  setDate(String d) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(d);
            String format = sdf.format(parse);
            String[] split = format.split("-");
            String s1 = split[0];
            String s2 = split[1];
            String s3 = split[2];
            sum = s1 + s2 + s3;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sum;
    }

}
