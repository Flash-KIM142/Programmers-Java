public class NDigitGame {
    static String solution(int n, int t, int m, int p) {
        String answer = "";

        String totalLine = "";
        int num = 0;
        while(totalLine.length()<=t*m){
            totalLine += Integer.toString(num++, n);
        }

        for(int i=0; i<t; i++)
            answer += totalLine.charAt(p-1+(m*i));

        answer = answer.toUpperCase();
        return answer;
    }

//    static String solution(int n, int t, int m, int p) {
//        String answer = "";
//        String totalLine = createTotalLine(n,t,m);
//        for(int i=0; i<t; i++){
//            int idx = (p-1) + (m * i);
//            answer += totalLine.charAt(idx);
//        }
//        return answer;
//    }

//    static String createTotalLine(int n, int t, int m){
//        String res = "";
//        int num = 0;
//        while(res.length()<=t*m){
//            String curNumStr = createNDigit(n, num);
//            res += curNumStr;
//            num++;
//        }
//        return res;
//    }
//
//    static String createNDigit(int n, int num){
//        if(num==0)  return "0";
//        String res = "";
//        while(num!=0){
//            int add = num % n;
//            if(add>=10){
//                res = (char)(add+55) + res;
//            }
//            else
//                res = add + res;
//            num /= n;
//        }
//        return res;
//    }

    public static void main(String[] args) {
        String answer = solution(16,16,2,1);
        System.out.println(answer);
    }
}