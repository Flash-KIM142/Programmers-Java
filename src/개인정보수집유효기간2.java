import java.util.*;

public class 개인정보수집유효기간2 {

    public static void main(String[] args) {
        String today = "2020.01.01";
        String[] terms = {"Z 3", "D 5"};
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};

        System.out.println(Arrays.toString(solution(today, terms, privacies)));
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> list = new ArrayList<>();

        Map<String, Integer> hm = new HashMap<>();
        for(String s: terms){
            String[] tmp = s.split(" ");
            hm.put(tmp[0], Integer.parseInt(tmp[1]));
        }

        int todayValue = convertDateToValue(today);

        for(int i=0; i<privacies.length; i++){
            String[] tmp = privacies[i].split(" ");
            int value = convertDateToValue(tmp[0]);
            value += hm.get(tmp[1]) * 28 - 1;

            if(value<todayValue)    list.add(i+1);
        }

        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++)    answer[i] = list.get(i);
        return answer;
    }

    static int convertDateToValue(String date){
        String[] ary = date.split("\\.");

        return Integer.parseInt(ary[0]) * 12 * 28 + Integer.parseInt(ary[1]) * 28 + Integer.parseInt(ary[2]);
    }
}
