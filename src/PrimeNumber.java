import java.io.*;

public class PrimeNumber {
    static int solution(int n, int k) {
        int answer = 0;
        String s = transform(n, k);
        String[] nums = s.split("0");
        for (String num : nums) {
            if (num.equals("")) continue;
            if(isPrime(Long.parseLong(num)))    answer++;
        }

        return answer;
    }

    static String transform(int n, int k) {
        String s = "";
        while (n != 0) {
            int remainder = n % k;
            s = remainder + s;
            n /= k;
        }
        return s;
    }

    static Boolean isPrime(Long num) {
        if(num==1)  return false;
        Long end = (long) Math.sqrt(num);
        for (long i = 2; i <= end; i++)
            if (num % i == 0)
                return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = 437674;
        int k = 3;
        int result = solution(n, k);
        bfw.write(result + "");
        bfw.close();
    }
}