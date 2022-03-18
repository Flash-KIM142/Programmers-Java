import java.util.ArrayList;
import java.util.HashSet;

public class Abuser {
    static HashSet<String> set = new HashSet<>();
    static boolean[] visited;
    static ArrayList<Integer>[] list;

    static int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];
        list = new ArrayList[banned_id.length];
        for (int i=0; i<banned_id.length; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            String ban = banned_id[i];
            for (int j = 0; j < user_id.length; j++) {
                String cur = user_id[j];
                if (cur.length() != ban.length()) continue;

                int flag = 0;
                for (int k = 0; k < ban.length(); k++) {
                    if (ban.charAt(k) != cur.charAt(k) && ban.charAt(k) != '*') break;
                    flag++;
                }
                if (flag == ban.length()) tmp.add(j);
            }
            list[i] = tmp;
        }

        combination(0, banned_id.length, user_id.length);
        return set.size();
    }

    static void combination(int cnt, int n, int length){
        if(cnt==n){
            String s = "";
            for(int i=0; i<length; i++){
                if(visited[i])  s += i+"";
            }
            set.add(s);
            return;
        }

        ArrayList<Integer> cur = list[cnt];
        for(int i=0; i<cur.size(); i++){
            if(visited[cur.get(i)]) continue;

            visited[cur.get(i)] = true;
            combination(cnt+1, n, length);
            visited[cur.get(i)] = false;
        }
    }

    public static void main(String args[]) {
        String[] users = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned = {"fr*d*", "abc1**"};

        System.out.println(solution(users,banned));
    }
}