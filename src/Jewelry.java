import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;

public class Jewelry {
    static HashSet<String> types = new HashSet<>();
    static HashMap<String, Integer> map = new HashMap<>();

    static int[] solution(String[] gems) {
        getTypes(gems);
        int typeSize = types.size();
        if(typeSize==1) return new int[]{1, 1};

        int gemsLen = gems.length;
        if(typeSize==gemsLen) return new int[]{1, gemsLen};

        int start=0, end=0; // 정답
        int left=0, right=0; // 검사용
        int len = Integer.MAX_VALUE;

        while(true){
            if(map.size()==typeSize){
                map.put(gems[left], map.get(gems[left])-1);
                if(map.get(gems[left])==0)
                    map.remove(gems[left]);
                left++;
            }
            else if(right==gemsLen) {
                break;
            }
            else{
                map.put(gems[right], map.getOrDefault(gems[right++], 0) + 1);
            }

            if(right-left < len && map.size()==typeSize){
                len = right - left;
                start = left + 1;
                end = right;
            }
        }

        return new int[]{start, end};
    }

    static void getTypes(String[] gems){
        types.addAll(Arrays.asList(gems));
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] gems = {"A", "B" ,"B", "C", "A", "B", "C", "A","B","C"};
        int[] answer = solution(gems);
        bfw.write(answer[0] + " " +answer[1]);
        bfw.close();
    }
}