import java.util.ArrayList;
import java.util.HashSet;

public class Abuser2 {

    static ArrayList<Integer>[] candidates;
    static HashSet<String> set = new HashSet<>();
    static boolean[] visited;

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};
        System.out.println(solution(user_id, banned_id));
    }

    static int solution(String[] user_id, String[] banned_id) {
        candidates = createPossibleAbusers(user_id, banned_id);
        visited = new boolean[user_id.length];

        backtracking(0, user_id.length, banned_id.length);

        return set.size();
    }

    static ArrayList<Integer>[] createPossibleAbusers(String[] user_id, String[] banned_id){
        ArrayList<Integer>[] result = new ArrayList[banned_id.length];

        for(int ban=0; ban<banned_id.length; ban++){
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int user=0; user<user_id.length; user++){
                boolean flag = true;

                int length = banned_id[ban].length();
                if(user_id[user].length()!=length)  continue;
                for(int i=0; i<length; i++){
                    if(banned_id[ban].charAt(i)=='*')    continue;
                    if(banned_id[ban].charAt(i)!=user_id[user].charAt(i)){
                        flag = false;
                        break;
                    }
                }

                if(flag)    tmp.add(user);
            }
            result[ban] = new ArrayList<>(tmp);
        }

        return result;
    }

    static void backtracking(int cnt, int user_size, int ban_size){
        if(cnt==ban_size){
            StringBuilder sb = new StringBuilder();

            for(int i=0; i< visited.length; i++)
                if(visited[i])  sb.append(i);

            set.add(sb.toString());

            return;
        }

        ArrayList<Integer> curCandidates = candidates[cnt];
        for(int i=0; i<curCandidates.size(); i++){
            if (visited[curCandidates.get(i)])  continue;

            visited[curCandidates.get(i)] = true;
            backtracking(cnt+1, user_size, ban_size);
            visited[curCandidates.get(i)] = false;
        }
    }
}
