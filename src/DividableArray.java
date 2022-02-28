import java.util.*;

public class DividableArray {
    static int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            if(arr[i]%divisor==0){ // 나누어 떨어지면
                list.add(arr[i]);
            }
        }

        if(list.size()==0)  list.add(-1);
        Collections.sort(list);

        int[] answer = new int[list.size()];
        for(int i=0; i<list.size();i++){
            answer[i] = list.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {5,9,7,10};
        int divisor = 5;
        int[] result = solution(arr, divisor);
        for(int a: result) System.out.println(a);
    }
}