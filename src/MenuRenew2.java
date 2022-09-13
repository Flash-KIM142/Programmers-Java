import java.util.*;

public class MenuRenew2 {
    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<Integer, Integer> maxMap = new HashMap<>();

    public static void main(String[] args) {
        String[] orders = {"XYZ", "XWY", "WXA"};
        String[] arrangedOrders = new String[orders.length];
        for (int i=0; i<orders.length; i++) {
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            String newOrder = Arrays.toString(chars);
            System.out.println(newOrder);

            arrangedOrders[i] = newOrder;
        }
        int[] course = {2,3,4};

        String[] result = solution(orders, course);
        for (String s : result) {
            System.out.println(s);
        }

    }

    static String[] solution(String[] orders, int[] course) {
        HashMap<Integer, Integer> candi = new HashMap<>();
        for(int n: course){
            candi.put(n, 0);
            maxMap.put(n, 0);
        }

        String[] arrangedOrders = new String[orders.length];
        for(int i=0; i<orders.length; i++){
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            arrangedOrders[i] = Arrays.toString(chars);
        }

        for(String order: arrangedOrders){
            createCourse(order, course);
        }

        for(Map.Entry<String, Integer> entry: map.entrySet()){
            int len = entry.getKey().length();
            int n = entry.getValue();

            if(maxMap.get(len)<n){
                maxMap.replace(len,n);
            }
        }

        List<String> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            int len = entry.getKey().length();
            int n = entry.getValue();

            if(maxMap.get(len)==n && n>=2){
                list.add(entry.getKey());
            }
        }

        Collections.sort(list);
        String[] answer = list.toArray(new String[list.size()]);

        return answer;
    }

    static void createCourse(String order, int[] course){
        for(int len: course){
            if(order.length()>=len){
                combination(order, len, 0, new int[order.length()], 0);
            }
        }
    }

    static void combination(String order, int n, int cnt, int[] idx, int cur){
        if(cnt==n){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<order.length(); i++){
                if(idx[i]==1){
                    sb.append(order.charAt(i));
                }
            }

            String course = sb.toString();

            if(!map.containsKey(course)){
                map.put(course, 1);
            }
            else{
                map.replace(course, map.get(course)+1);
            }

            return;
        }

        for(int i=cur; i<order.length(); i++){
            idx[i] = 1;
            combination(order, n, cnt+1, idx, i+1);
            idx[i] = 0;
        }
    }
}
