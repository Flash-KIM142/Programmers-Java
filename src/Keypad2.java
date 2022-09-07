public class Keypad2 {

//    static Pos[] coord = new Pos[10];

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";

        System.out.println(solution(numbers, hand));
    }

    static String solution(int[] numbers, String hand) {
        String answer = "";
        Pos[] coord = new Pos[10];

        coord[0] = new Pos(3,1);
        coord[1] = new Pos(0,0);
        coord[2] = new Pos(0,1);
        coord[3] = new Pos(0,2);
        coord[4] = new Pos(1,0);
        coord[5] = new Pos(1,1);
        coord[6] = new Pos(1,2);
        coord[7] = new Pos(2,0);
        coord[8] = new Pos(2,1);
        coord[9] = new Pos(2,2);

        Pos left = new Pos(3,0);
        Pos right = new Pos(3,2);


        for (int number : numbers) {
            if(number==1 || number==4 || number==7){
                answer += "L";
                left = coord[number];
            }
            else if(number==3 || number==6 || number==9){
                answer += "R";
                right = coord[number];
            }
            else{
                int leftDist = left.getDistance(coord[number]);
                int rightDist = right.getDistance(coord[number]);
                if(leftDist==rightDist){
                    if (hand.equals("right")) {
                        answer += "R";
                        right = coord[number];
                    }
                    else{
                        answer += "L";
                        left = coord[number];
                    }
                }
                else{
                    if(leftDist>rightDist){
                        answer += "R";
                        right = coord[number];
                    }
                    else{
                        answer += "L";
                        left = coord[number];
                    }
                }
            }
        }

        return answer;
    }


    static class Pos{
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        int getDistance(Pos p){
            return Math.abs(p.row - this.row) + Math.abs(p.col - this.col);
        }
    }
}
