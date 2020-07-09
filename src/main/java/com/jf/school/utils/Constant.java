package com.jf.school.utils;

public class Constant {
    public static class ExamStatus {
        //        0 为初始化
        //        1 未开始考试
        //        2 开始考试中
        //        3 考试结束
        public static final int UN_INIT = 0;
        public static final int NOT_RUN = 1;
        public static final int RUN_ING = 2;
        public static final int RUN_END = 3;
    }

    public static class UserType {
        public static final int ADMIN = 1;
        public static final int STAFF = 2;
        public static final int MANAGER = 3;

    }

    public static class QuestionType {
        public final static int SINGLE = 1;
        public final static int MULTI = 2;
        public final static int JUDGE = 3;
        public final static int FILL = 4;
        public final static int SHORT_ANSWER = 5;
    }


}
