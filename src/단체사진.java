import java.util.*;

public class 단체사진 {

    static public int solution(int n, String[] data) {
        char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        char[] curr = new char[friends.length];
        boolean[] visited = new boolean[friends.length];
        List<String> candidates = new ArrayList<>();
        permutation(friends, curr, visited, 0, 8, candidates);

        int answer = 0;

        L1: for(String s: candidates){
            for(String cond: data){
                if(!isPossible(s, cond))    continue L1;
            }
            answer++;
        }

        return answer;
    }

    static public void permutation(char[] arr, char[] curr, boolean[] visited, int depth, int r, List<String> candidates){
        if(depth==r){
            StringBuilder sb = new StringBuilder();
            for(char c: curr)   sb.append(c);

            candidates.add(sb.toString());
            return;
        }

        for(int i=0; i<arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                curr[depth] = arr[i];
                permutation(arr, curr, visited, depth+1, r, candidates);
                visited[i] = false;
            }
        }
    }

    static public Boolean isPossible(String s, String cond){
        int meIdx = s.indexOf(cond.charAt(0));
        int youIdx = s.indexOf(cond.charAt(2));
        char operator = cond.charAt(3);
        int num = cond.charAt(4) - '0';

        switch(operator){
            case '=':
                if(Math.abs(meIdx-youIdx)!=num+1) return false;
                break;

            case '>':
                if(Math.abs(meIdx-youIdx)<=num+1) return false;
                break;

            case '<':
                if(Math.abs(meIdx-youIdx)>=num+1) return false;
                break;
        }

        return true;
    }
}