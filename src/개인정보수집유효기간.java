import java.util.*;

public class 개인정보수집유효기간 {

    public static void main(String[] args) {
        String today = "2022.05.19";

        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2022.02.19 C", };
        System.out.println(solution(today, terms, privacies));
    }

    static int[] solution(String today, String[] terms, String[] privacies){
        Date todayDate = getDate(today);
        int todayToDays = transformToDays(todayDate);
        HashMap<Character, Integer> typeInfo = new HashMap<>();

        for(String term: terms){
            String[] tmp = term.split("\\s");
            typeInfo.put(tmp[0].charAt(0), Integer.parseInt(tmp[1]));
        }

        List<Integer> ansList = new ArrayList<>();
        for(int i=0; i<privacies.length; i++){
            String[] privacy = privacies[i].split("\\s");
            Date date = getDate(privacy[0]);
            Character type = privacy[1].charAt(0);

            int expirationDays = getExpirationDays(date, typeInfo.get(type));

            if(todayToDays>=expirationDays) ansList.add(i+1);
        }

        int[] ans = new int[ansList.size()];
        for(int i=0; i< ansList.size(); i++){
            ans[i] = ansList.get(i);
        }

        return ans;
    }

    static class Date{
        int year;
        int month;
        int day;

        Date(int year, int month, int day){
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }

    static Date getDate(String s){
        String[] str = s.split("\\.");
        int[] info = new int[3];

        for(int i=0; i<3; i++)
            info[i] = Integer.parseInt(str[i]);

        return new Date(info[0], info[1], info[2]);
    }

    static int transformToDays(Date date){
        return date.year*12*28 + date.month*28 + date.day;
    }

    static int getExpirationDays(Date date, int n){
        return transformToDays(date) + n*28;
    }
}
