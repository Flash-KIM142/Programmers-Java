// 2023.11.09 3차 시도

public class 숫자문자열과영단어 {

    static String[] numberString = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) {
        int result = solution("12zero3zero");
        System.out.println(result);
    }

    public static int solution(String s) {
        for(int i=0; i<9; i++){
            s = s.replace(numberString[i], String.valueOf(i));
        }

        return Integer.parseInt(s);
    }
}