import java.util.ArrayList;

public class DartGame {
    static ArrayList<Info> list = new ArrayList<>();
    static ArrayList<Integer> score = new ArrayList<>(list.size());

    static int solution(String dartResult) {
        int answer = 0;
        String[] tmp1 = (dartResult.split("\\D+"));
        String[] tmp2 = dartResult.split("[0-9]+");
        for(int i=0; i<tmp1.length; i++){
            int score = Integer.parseInt(tmp1[i]);
            list.add(new Info(score, tmp2[i+1]));
        }

        getScore();
        for(int s: score)   answer += s;

        return answer;
    }

    static void getScore(){
        for(int i=0; i<list.size(); i++){
            Info cur = list.get(i);
            int curScore = 0;
            switch(cur.type.charAt(0)){
                case 'S':
                    curScore += cur.score;
                    break;
                case 'D':
                    curScore += (cur.score * cur.score);
                    break;
                case 'T':
                    curScore += (cur.score * cur.score * cur.score);
                    break;
            }

            if(cur.type.length()==2){ // 옵션도 붙었을 때
                if(cur.type.charAt(1)=='*') {
                    curScore *= 2;
                    if(i>0)    score.set(i-1, score.get(i-1)*2);
                }
                else    curScore *= -1;
            }

            score.add(i, curScore);
        }
    }

    static class Info{
        int score;
        String type;

        Info(int score, String type){
            this.score = score;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        String dartResult = "1D#2S*3S";
        int answer = solution(dartResult);
        System.out.println(answer);
    }
}