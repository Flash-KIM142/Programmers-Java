package leetcode;

import java.util.*;

public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        StringBuilder num1_sb = new StringBuilder(num1);
        StringBuilder num2_sb = new StringBuilder(num2);
        num1_sb.reverse();
        num2_sb.reverse();

        List<List<Integer>> one_digit_multiply_list = new ArrayList<>();
        for(int i=0; i<num2_sb.length(); i++) {
            one_digit_multiply_list.add(
                    one_digit_multiply(num1_sb, num2_sb.charAt(i), i)
            );
        }

        StringBuilder answer = new StringBuilder();
        int answer_len = one_digit_multiply_list.get(one_digit_multiply_list.size()-1).size();
        List<Integer> each_digits = new ArrayList<>();
        for(int i=0; i<answer_len; i++) {
            each_digits.add(0);
        }

        for(List<Integer> multiply: one_digit_multiply_list) {
            for(int i=0; i<multiply.size(); i++) {
                each_digits.set(i, each_digits.get(i) + multiply.get(i));
            }
        }

        for(int i=0; i<answer_len; i++) {
            if(each_digits.get(i) >= 10) {
                if(i+1==answer_len) {
                    each_digits.add(0);
                }
                each_digits.set(i+1, each_digits.get(i+1) + each_digits.get(i) / 10);
                each_digits.set(i, each_digits.get(i) % 10);
            }
            answer.append(each_digits.get(i));
        }
        if(each_digits.size() > answer_len) {
            answer.append(each_digits.get(answer_len));
        }
        return answer.reverse().toString();
    }

    public List<Integer> one_digit_multiply(StringBuilder num1, char num2_one_digit, int num_zero) {
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<num_zero; i++) {
            result.add(0);
        }

        int carry = 0;
        for(int i=0; i<num1.length(); i++) {
            char num1_cur_digit = num1.charAt(i);
            int multiplication = (num1_cur_digit - '0') * (num2_one_digit - '0') + carry;
            carry = multiplication / 10;
            result.add(multiplication % 10);
        }

        if(carry>0) {
            result.add(carry);
        }

        return result;
    }
}
