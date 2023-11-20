// 2023.11.20 2023 카카오 블라인드

public class 표현가능한이진트리2 {

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        int idx = 0;
        for (long number : numbers) {
            String binary = Long.toBinaryString(number);

            int i = 0;
            while ((int) Math.pow(2, i) - 1 < binary.length()) {
                i++;
            }
            while ((int) Math.pow(2, i) - 1 != binary.length()) {
                binary = "0" + binary;
            }

            if (isConvertibleToBT(binary)) {
                answer[idx++] = 1;
            } else {
                answer[idx++] = 0;
            }
        }
        return answer;
    }

    static boolean isConvertibleToBT(String s) {
        if (s.length() == 1) {
            return true;
        }

        String left = s.substring(0, s.length() / 2);
        String right = s.substring(s.length() / 2 + 1);
        if (isRootEmpty(s) && (!isRootEmpty(left) || !isRootEmpty(right))) {
            return false;
        }

        return isConvertibleToBT(left) && isConvertibleToBT(right);
    }

    static boolean isRootEmpty(String s) {
        return s.charAt(s.length() / 2) == '0';
    }

    public static void main(String[] args) {
        int[] result = solution(new long[]{5});
        for (int n : result) {
            System.out.println(n);
        }
    }
}