import java.io.*;
import java.util.*;

public class Tuple {
    static int[] solution(String s) {
        LinkedList<ArrayList<Integer>> list = parser(s);
        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        for(int i=0; i<list.size(); i++)
            for(int j=0; j<list.get(i).size(); j++)
                set.add(list.get(i).get(j));

        int[] answer = new int[set.size()];
        Iterator<Integer> it = set.iterator();
        for(int i=0; i<set.size(); i++)
            answer[i] = it.next();
        return answer;
    }

    static LinkedList<ArrayList<Integer>> parser(String s){
        LinkedList<ArrayList<Integer>> result = new LinkedList<>();
        int length = s.length();
        int listPos = 0;
        ArrayList<Integer> tmp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<length-1; i++){ // 어차피 양쪽 끝은 중괄호로 감싸져 있으니 무시
            char cur = s.charAt(i);
            if(cur=='{')    continue;
            if(cur==','){
                char next = s.charAt(i+1);
                if(next=='{'){
                    i++;
                    continue;
                }
                else{
                    tmp.add(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                    continue;
                }
            }
            if(cur=='}') {
                tmp.add(Integer.parseInt(sb.toString()));   sb = new StringBuilder();
                ArrayList<Integer> copy = new ArrayList<>();    copy.addAll(tmp);   tmp.clear();
                result.add(listPos, copy);  listPos++;
                continue;
            } // 한 부분집합이 끝났으면 다음 부분집합 위치로
            if(cur>='0' && cur<='9')
                sb.append(cur);
        }
//        result.sort(new Comparator<ArrayList<Integer>>() {
//            @Override
//            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
//                if(o1.size()>o2.size()) return 1;
//                else    return -1;
//            }
//        });

        Collections.sort(result, (o1,o2) -> { return Integer.compare(o1.size(), o2.size());});

        return result;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        for(int e: solution(input))
            bfw.write(e + " ");
        bfw.close();
    }
}