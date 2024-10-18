import java.util.*;

public class 문자열압축 {

    List<Integer> candidates = new ArrayList<>();

    public int solution(String s) {
        candidates.add(s.length());

        for(int i=1; i<=s.length()/2; i++) {
            compress(i, s);
        }

        Collections.sort(candidates);
        return candidates.get(0);
    }

    void compress(int size, String s) {
        StringBuilder sb = new StringBuilder();
        Queue<String> q = new LinkedList<>();
        int idx = 0;
        while(true) {
            if(idx+size<=s.length()) {
                q.add(s.substring(idx, idx+size));
            }
            else {
                q.add(s.substring(idx));
                break;
            }
            idx += size;
        }

        String prev = q.poll();
        int cnt = 1;
        while(!q.isEmpty()) {
            String cur = q.poll();
            if(prev.equals(cur)) {
                cnt++;
            }
            else {
                if(cnt==1) {
                    sb.append(prev);
                }
                else {
                    sb.append(cnt).append(prev);
                }
                cnt = 1;
            }

            prev = cur;
            if(q.isEmpty()) {
                if(cnt==1) {
                    sb.append(cur);
                }
                else {
                    sb.append(cnt).append(cur);
                }
            }
        }
        candidates.add(sb.length());
    }
}
