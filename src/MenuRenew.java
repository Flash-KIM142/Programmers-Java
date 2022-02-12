import java.util.*;

public class MenuRenew {
    static HashMap<String, Integer> map = new HashMap<>();
    static HashSet<String> set = new HashSet<>();

    static String[] solution(String[] orders, int[] course) {
        for(String order: orders){
            boolean[] visited = new boolean[order.length()];
            for(int i=0; i<course.length; i++)
                combination(createSortedString(order), visited, 0, course[i]);
        }

        for(int i=0; i<course.length; i++){
            int length = course[i];
            int max = 0;
            HashSet<String> tmpSet = new HashSet<>();
            for(String key: map.keySet()){
                if(key.length()!=length)    continue;
                if(map.get(key)==max)   tmpSet.add(key);
                else if(map.get(key)>max && map.get(key)>1){
                    tmpSet.clear();
                    tmpSet.add(key);
                    max = map.get(key);
                }
            }
            set.addAll(tmpSet);
        }

        String[] answer = set.toArray(new String[set.size()]);
        Arrays.sort(answer);
        return answer;
    }

    static void combination(String order, boolean[] visited, int cur, int r){
        if(r==0){
            String comb = "";
            for(int i=0; i<visited.length; i++)
                if(visited[i])  comb += order.charAt(i);

            if(map.containsKey(comb))   map.put(comb, map.get(comb)+1);
            else    map.put(comb, 1);
            return;
        }
        for(int i=cur; i<order.length(); i++){
            visited[i] = true;
            combination(order, visited, i+1, r-1);
            visited[i] = false;
        }
    }

    static String createSortedString(String s){
        String res = "";
        ArrayList<Character> list = new ArrayList<>();
        for(int i=0; i<s.length(); i++)
            list.add(s.charAt(i));
        Collections.sort(list);
        for(char ch: list)
            res += ch;

        return res;
    }

    public static void main(String[] args) {
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};
        String[] answer = solution(orders, course);
        for(String s: answer) System.out.println(s);
    }
}