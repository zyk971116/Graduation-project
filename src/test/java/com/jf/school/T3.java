package com.jf.school;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.text.ParseException;

public class T3 {

    public static void main(String[] args) throws ParseException {

        int x= 0;
        int y=10;
        do{
            y--;
            ++x;
        }while(x<6);
        System.out.println(x+","+y);

    }

}


