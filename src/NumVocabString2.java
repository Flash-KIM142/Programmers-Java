public class NumVocabString2 {

    static String[] ary = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) {
        String s = "123";

//        String target = "one";
//        s = s.replaceAll(target, "1");
//        System.out.println(s);
        System.out.println(solution(s));
    }

    static int solution(String s) {
        for(int i=0; i<10; i++){
            s = s.replaceAll(ary[i], String.valueOf(i));
        }

        return Integer.parseInt(s);
    }

}
