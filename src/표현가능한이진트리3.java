import java.util.Stack;

public class 표현가능한이진트리3 {

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        /* 주어진 수 하나씩 판별하기 */
        for(int i=0; i<numbers.length; i++){
            long cur = numbers[i];
            boolean is_treeable = true;

            /* 판별 */
            String binary_string = decimal_to_binary_string(cur);
            int len = binary_string.length();
            is_treeable = judge_is_treeable(binary_string, len);

            if(is_treeable){
                answer[i] = 1;
            }
            else{
                answer[i] = 0;
            }
        }

        return answer;
    }

    boolean judge_is_treeable(String binary_string, int len){
        if(len==1){
            return true;
        }

        /* 현재 root가 더미인데 자식을 가지면 false 반환. 그렇지 않으면 왼쪽 자식 체크하고, 오른쪽 자식 체크하고 */
        int root_pos = len / 2;
        int left_child_pos = root_pos / 2;
        int right_child_pos = (len/2 + len) / 2;

        char root = binary_string.charAt(root_pos);
        char left_child = binary_string.charAt(left_child_pos);
        char right_child = binary_string.charAt(right_child_pos);

        if(root=='0'){
            if(left_child=='1' || right_child=='1'){
                return false;
            }
        }

        boolean tmp1 = judge_is_treeable(binary_string.substring(0, root_pos), root_pos);
        boolean tmp2 = judge_is_treeable(binary_string.substring((root_pos+1)), root_pos);

        return tmp1&&tmp2;
    }

    String decimal_to_binary_string(long n){
        String res = "";
        Stack<Integer> remainder = new Stack<>();
        while(n>0){
            Long tmp = n % 2;
            remainder.add(tmp.intValue());
            n/=2;
        }

        /* 2^n - 1 의 길이를 맞춰줘야함 */
        int i = 2;
        while(true){
            if(i - 1 == remainder.size()){
                break;
            }
            else if(i - 1 < remainder.size() && i*2 - 1 > remainder.size()){
                for(int j=0; j<i*2 - 1 - remainder.size(); j++){
                    res += "0";
                }
                break;
            }
            else{
                i *= 2;
            }
        }

        while(!remainder.isEmpty()){
            res += remainder.pop() + "";
        }

        return res;
    }
}
