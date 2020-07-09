package com.jf.school;

import com.jf.school.utils.DateUtils;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class T {
    public static void main(String[] args) throws ParseException {

        String password = "201" + "111111";
        String p = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(p);

       /* BigDecimal bd =new BigDecimal("6.437598");
        bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(bd);

        String date = "2020年04月";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy年MM月").parse(date));

        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 设置日历中月份最小天数
        calendar.set(Calendar.DAY_OF_MONTH, firstDay);
        System.out.println(DateUtils.getUnixTimeStamp(calendar.getTimeInMillis()));

        calendar.set(Calendar.DAY_OF_MONTH, endDay);
        System.out.println(DateUtils.getUnixTimeStamp(calendar.getTimeInMillis()));*/
        int[] numbers = new int[] { 5, 7, 3, 8, 9, 11 };
        int temp = 0;
        int size = numbers.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) // 交换两数位置
                {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        // 打印排序结果
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + "  ");
        }


    }

}


