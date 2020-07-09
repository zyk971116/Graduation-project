package com.jf.school.utils;

import org.springframework.stereotype.Component;

@Component
public class AnswerUtils {
    static String[] choiceDisplay = {"一等品", "二等品", "三等品"};
    static String[] categoryDisplay = {"金属材料类", "化工建材类", "油脂材料类"};

    public static String getDisplayAnswer(String quality) {
        // 1,2
        String[] as = quality.split(",");
        StringBuilder sb = new StringBuilder();
        for (String a : as) {
            sb.append(choiceDisplay[Integer.parseInt(a) - 1]).append(",");
        }
        //A,B,
        return sb.deleteCharAt(sb.length() - 1).toString();

    }

    public static String getCategoryDisplay(String category) {
        // 1,2
        String[] cs = category.split(",");
        StringBuilder sb = new StringBuilder();
        for (String a : cs) {
            sb.append(categoryDisplay[Integer.parseInt(a) - 1]).append(",");
        }
        //A,B,
        return sb.deleteCharAt(sb.length() - 1).toString();

    }



    public static void main(String[] args) {
        System.out.println(getDisplayAnswer("1"));
        System.out.println(getCategoryDisplay("1"));

        System.out.println(getDisplayAnswer("1,2,3"));
        System.out.println(getCategoryDisplay("1,2,3"));

    }
}
