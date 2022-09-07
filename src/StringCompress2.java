import java.util.*;

public class StringCompress2 {

    static int solution(String s) {
        List<Integer> list = new ArrayList<>();
        if(s.length()==1)   return 1;

        int len = s.length();
        for(int l=1; l<=len/2; l++){
            list.add(compress(l, s));
        }

        Collections.sort(list);
        return list.get(0);
    }

    static int compress(int len, String s){
        StringBuilder sb = new StringBuilder();
        Queue<String> q = new LinkedList<>(); // 길이별로 자른 문자열 담을 그릇

        int i = 0;

        while(i<=s.length()){
            if(i+len<=s.length()){
                q.add(s.substring(i, i+len));
            }
            else{
                q.add(s.substring(i));
            }
            i += len;
        }

        int cnt = 1;
        String prev = q.poll();
        while(!q.isEmpty()){
            String cur = q.poll();

            if(cur.equals(prev)){ // 전 문자열이랑 같으면 cnt 추가
                cnt++;
            }
            else{ // 다르면
                if(cnt==1){ // 전 문자열 한 번밖에 안 나왔으면
                    sb.append(prev);
                }
                else{
                    sb.append("" + cnt + prev);
                }
                cnt = 1;
            }

            prev = cur;

            if(q.size()==0){ // 마지막으로 뺀 놈 처리해주자
                if(cnt==1){
                    sb.append(cur);
                }
                else{
                    sb.append("" + cnt + cur);
                }
            }
        }


        return sb.toString().length();
    }
}
