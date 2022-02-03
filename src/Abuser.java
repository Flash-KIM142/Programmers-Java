import java.util.*;
import java.io.*;

public class Abuser {
    static HashSet<String> set = new HashSet<>();
    static ArrayList<Integer>[] possibleAbusers;
    static boolean[] visited;

    static int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];
        possibleAbusers = createPossibleAbusers(user_id, banned_id);
        backTracking(0, user_id.length, banned_id.length);
        return set.size();
    }

    static void backTracking(int cnt, int user_id_size, int banned_id_size){
        if(cnt==banned_id_size){
            String s = "";
            for(int i=0; i<user_id_size; i++)
                if(visited[i])  s += i;

            set.add(s);
            return;
        }

        ArrayList<Integer> cur = possibleAbusers[cnt];
        for(int i=0; i<cur.size(); i++){
            if(visited[cur.get(i)]) continue;
            visited[cur.get(i)] = true;
            backTracking(cnt+1, user_id_size, banned_id_size);
            visited[cur.get(i)] = false;
        }
    }

    static ArrayList<Integer>[] createPossibleAbusers(String[] user_id, String[] banned_id){
        ArrayList<Integer>[] result = new ArrayList[banned_id.length];
        for(int banned=0; banned<banned_id.length; banned++){
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int user=0; user<user_id.length; user++){
                boolean flag = true;
                if(banned_id[banned].length()!=user_id[user].length())  continue; // 서로 길이 다르면 패스
                for(int i=0; i<user_id[user].length(); i++){
                    if(banned_id[banned].charAt(i)=='*') continue;
                    if(user_id[user].charAt(i)!=banned_id[banned].charAt(i)) {
                        flag = false;
                        break;
                    }
                }
                if(flag)    tmp.add(user);
            }
            result[banned] = new ArrayList<>(tmp);
        }
        return result;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] users = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned = {"fr*d*", "abc1**"};

        bfw.write(String.valueOf(solution(users,banned)));
        bfw.close();
    }
}