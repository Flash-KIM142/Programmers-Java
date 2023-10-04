public class 두원사이의정수쌍 {

    public static void main(String[] args) {
        int r1 = 999999;
        int r2 = 1000000;
        System.out.println(solution(r1,r2));
    }

    static long solution(int r1, int r2) {
        long answer = 0;

        for(int x=1; x<r2; x++){
            int high = (int)Math.floor(Math.sqrt(r2*(long)r2 - x*(long)x));
            int low = 1;

            if(x<r1){
                low = (int)Math.ceil(Math.sqrt(r1*(long)r1 - x*(long)x));
            }

            answer += high - low + 1;
        }

        answer = (answer + r2 - r1 + 1)*4;

        return answer;
    }
}
