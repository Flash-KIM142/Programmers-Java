import java.util.*;

public class MenuRenew {
    static HashMap<String, Integer> hm = new HashMap<>();
    static int max;

    static String[] solution(String[] orders, int[] course){
        PriorityQueue<String> q = new PriorityQueue<>();

        for(int len: course){
            max = 0;
            hm = new HashMap<>();
            for(String order: orders){
                combination(0, len, "", 0, order);
            }
            for(String newMenu: hm.keySet()){
                if(hm.get(newMenu)==max && max>1){
                    q.add(newMenu);
                }
            }
        }

        String[] answer = new String[q.size()];
        int i=0;
        while(!q.isEmpty())
            answer[i++] = q.poll();

        return answer;
    }

    static void combination(int cnt, int len, String res, int idx, String order){
        if(cnt==len){
            char[] menus = res.toCharArray();
            Arrays.sort(menus);
            String newMenu = "";
            for(char unitMenu: menus)
                newMenu += unitMenu;
            hm.put(newMenu, hm.getOrDefault(newMenu, 0) + 1);
            max = Math.max(hm.get(newMenu), max);
        }
        for(int i=idx; i<order.length(); i++){
            char tmp = order.charAt(i);
            combination(cnt+1, len, res + tmp, i+1, order);
        }
    }

    public static void main(String[] args) {
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};
        String[] answer = solution(orders, course);
        for(String s: answer) System.out.println(s);
    }
}