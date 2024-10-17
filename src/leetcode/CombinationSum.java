package leetcode;

import java.util.*;

public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,5};
        int target = 8;
        List<List<Integer>> answer = combinationSum(candidates, target);
        for (List<Integer> list : answer) {
            for(Integer integer : list){
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();
        solution(candidates, target, 0, 0, stk, res);
        return res;
    }

    public static void solution(int[] candidates, int target, int idx, int sum, Stack<Integer> cur, List<List<Integer>> res) {
        if(sum==target) {
            Stack<Integer> tmp = new Stack<>();
            tmp.addAll(cur);
            List<Integer> list = new ArrayList<>(tmp);
            res.add(list);
            return;
        }

        for(int i=idx; i<candidates.length; i++) {
            if(candidates[i] + sum > target) {
                continue;
            }
            else {
                sum += candidates[i];
                cur.add(candidates[i]);
                solution(candidates, target, i, sum, cur, res);
                sum -= cur.pop();
            }
        }
    }
}
