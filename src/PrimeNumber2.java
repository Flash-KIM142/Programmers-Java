public class PrimeNumber2 {

    static int solution(int n, int k) {
        int answer = 0;
        String s = toKRadix(n,k);

        s = s.replaceAll("0+", " ");
        String[] split = s.split(" ");
        for(String cur: split){
            if(isPrime(Long.parseLong(cur)))  answer++;
        }

        return answer;
    }

    static boolean isPrime(long n){
        if(n==1)    return false;
        if(n==2)    return true;

        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i == 0)    return false;
        }

        return true;
    }

    static String toKRadix(int n, int k){
        StringBuilder sb = new StringBuilder();
        while(n>0){
            sb.append(n%k);
            n /= k;
        }

        return sb.reverse().toString();
    }
}
