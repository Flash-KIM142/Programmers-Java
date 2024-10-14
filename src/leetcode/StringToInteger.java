package leetcode;

public class StringToInteger {

    public static void main(String[] args) {
        String s = "20000000000000000000";
        int res = myAtoi(s);
        System.out.println(res);
    }

    public static int myAtoi(String s) {
        if(s.isEmpty()) {
            return 0;
        }

        int idx = 0;
        int res = 0;
        int sign = 1;

        while(idx < s.length() && s.charAt(idx)==' ') {
            idx++;
        }

        if(idx==s.length()) {
            return 0;
        }

        if(s.charAt(idx)=='+' || s.charAt(idx)=='-') {
            sign = s.charAt(idx) == '+' ? 1 : -1;
            idx++;
        }

        while(idx < s.length()) {
            int digit = s.charAt(idx) - '0';
            if(digit<0 || digit>9) {
                break;
            }

            if(res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && digit > Integer.MAX_VALUE%10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + digit;
            idx++;
        }

        return res * sign;
    }
}
