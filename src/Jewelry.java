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
        if(typeSize==1) return new int[]{1, 1}; // 종류 하나밖에 없으면 바로 (1,1) 리턴

        int gemsLen = gems.length;
        if(typeSize==gemsLen) return new int[]{1, gemsLen}; // 종류 수랑 전체 보석 개수 똑같으면 처음부터 끝까지 바로 반환

        int start=0, end=0; // 정답용
        int left=0, right=0; // 검사용
        int len = Integer.MAX_VALUE;

        while(true){
            if(map.size()==typeSize){
                map.put(gems[left], map.get(gems[left])-1);
                if(map.get(gems[left])==0)
                    map.remove(gems[left]);
                left++; // 왼쪽 커서 옮겨주기
            }
            else if(right==gemsLen) // 끝까지 갔으면 종료
                break;
            else
                map.put(gems[right], map.getOrDefault(gems[right++], 0) + 1); // 오른쪽 커서 옮겨주기

            if(right-left < len && map.size()==typeSize){ // 새로운 구간 찾으면 갱신해주기
                len = right - left;
                start = left + 1; // 0부터가 아니라 1부터니까 1 추가
                end = right; // 오른쪽 커서는 이미 한 칸 커졌으니까 놔둠
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