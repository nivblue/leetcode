package leetcode.a;

public class Base7 {
    public static void help(String args[]) {
//        int num = 100; // 202
//        int num = -7; // -10
        int num = -9; // -10
//        int num = 9; // 12
//        int num = 0; // "0"
//        int num = -8; // "-11"

        System.out.println(convertToBase7(num));
    }

    public static String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }

//        int result = 0;
//        int count = 0;
//
//        while (num != 0) {
//            result += ((num % 7)*Math.pow(10,count));
//
//            num /= 7;
//            count++;
//        }
//
//        return String.valueOf(result);

        StringBuilder stringBuilder = new StringBuilder();
        boolean isNeg = num < 0;
        num = Math.abs(num);

        while (num != 0) {
            stringBuilder.append(num % 7);
            num /= 7;
        }

        if (isNeg) stringBuilder.append( "-");

        return stringBuilder.reverse().toString();
    }
}
