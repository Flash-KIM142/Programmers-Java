import java.io.*;

public class Keypad {
    static int[] whereLeft = {3,0};
    static int[] whereRight = {3,2};
    static int[][] keypad = { {3,1}, {0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2} };
    
    static String solution(int[] numbers, String hand) {
        String answer = "";
        for(int num: numbers){
            switch(num){
                case 1: case 4: case 7:
                    answer += "L";
                    whereLeft = keypad[num];
                    break;
                case 3: case 6: case 9:
                    answer += "R";
                    whereRight = keypad[num];
                    break;
                default:
                    answer += whichHand(num, hand);
            }
        }
        return answer;
    }

    static String whichHand(int num, String hand){
        int[] target = keypad[num];
        int distanceFromLeft = Math.abs(whereLeft[0]-target[0]) + Math.abs(whereLeft[1]-target[1]);
        int distanceFromRight = Math.abs(whereRight[0]-target[0]) + Math.abs(whereRight[1]-target[1]);
        if(distanceFromLeft<distanceFromRight){ // 왼손이 더 가까울 때
            whereLeft = target;
            return "L";
        }
        else if(distanceFromLeft>distanceFromRight){ // 오른손이 더 가까울 때
            whereRight = target;
            return "R";
        }
        else{ // 거리 같을 때
            if(hand.equals("left")){
                whereLeft = target;
                return "L";
            }
            else{
                whereRight = target;
                return "R";
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};  String hand = "right";
        bfw.write(solution(numbers, hand));
        bfw.close();
    }
}