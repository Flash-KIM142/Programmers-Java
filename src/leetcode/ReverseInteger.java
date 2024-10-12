package leetcode;

public class ReverseInteger {

    public static void main(String[] args) {
        int x = -2147483648;
        int res = reverse(x);
        System.out.println(res);
    }

    public static int reverse(int x) {
        int res = 0;
        while(x!=0) {
            int remainder = x % 10;
            if(res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {
                return 0;
            }

            res = res * 10 + remainder;
            x /= 10;
        }

        return res;
    }
}
