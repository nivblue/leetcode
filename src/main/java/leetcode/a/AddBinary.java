package leetcode.a;

public class AddBinary {
    public static void test() {
        //        String a = "1", b = "11"; // 100
        String a = "1010", b = "1011"; // 10101
    }

    public static String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        char carry = '0';

        StringBuilder stringBuilder = new StringBuilder(Math.max(a.length(), b.length()) + 1);


        while (i >= 0 || j >= 0 || carry == '1') {
            char ac = i >= 0 ? a.charAt(i--) : '0';
            char bc = j >= 0 ? b.charAt(j--) : '0';
            char current = '0';

            if (ac == '1' && bc == '1') { // 1 + 1
                if (carry == '1') {
                    current = '1';
                }

                carry = '1';
            } else if ((ac == '0' && bc == '1') || (ac == '1' && bc == '0')) { // 0 + 1
                if (carry == '0') {
                    current = '1';
                }
            } else { // 0 + 0
                if (carry == '1') {
                    current = '1';
                    carry = '0';
                }
            }

            stringBuilder.insert(0, current);
        }

        return stringBuilder.toString();
    }
}
