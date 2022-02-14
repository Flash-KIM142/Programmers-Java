public class Lottery {
    static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int howManyZero = 0;
        for (int myNum : lottos)
            if (myNum == 0) howManyZero++;

        int alreadyHit = 0;
        for (int i = 0; i < win_nums.length; i++) {
            for (int myNum : lottos) {
                if (myNum == win_nums[i]) { // 번호가 일치하는 게 나오면
                    alreadyHit++;
                    break;
                }
            }
        }

        answer[0] = (7 - (howManyZero+alreadyHit) >= 6 ? 6 : 7 - (howManyZero+alreadyHit));
        answer[1] = (7 - alreadyHit >= 6 ? 6 : 7 - alreadyHit);
        return answer;
    }

    public static void main(String[] args) {
        int[] lottos = {1,2,3,4,5,6};
        int[] win_nums = {7,8,9,10,11,12};
        int[] answer = solution(lottos, win_nums);
        for (int a : answer) System.out.println(a);
    }
}