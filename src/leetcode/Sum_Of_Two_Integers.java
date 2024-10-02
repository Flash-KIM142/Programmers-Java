package leetcode;

public class Sum_Of_Two_Integers {

    public static void main(String[] args) {
        int a = 1;
        int b = 3;
        int res = getSum(a, b);
        System.out.println(res);
    }

    static int getSum(int a, int b) {
        if(a==0) {
            return b;
        }
        if(b==0) {
            return a;
        }

        while(b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }
}
