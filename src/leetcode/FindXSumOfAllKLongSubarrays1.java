package leetcode;

import java.util.*;

public class FindXSumOfAllKLongSubarrays1 {

    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length - k + 1;
        int[] res = new int[n];

        if(k<=x) {
            for(int i=0; i<n; i++) {
                int tmp_sum = 0;
                for(int j=i; j<i+k; j++) {
                    tmp_sum += nums[j];
                }
                res[i] = tmp_sum;
            }

            return res;
        }

        for(int i=0; i<n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int j=i; j<i+k; j++) {
                if(!map.containsKey(nums[j])) {
                    map.put(nums[j], 0);
                }
                map.put(nums[j], map.get(nums[j])+1);
            }

            List<Node> list = new ArrayList<>();
            for(Integer key: map.keySet()) {
                list.add(new Node(key, map.get(key)));
            }
            Collections.sort(list);

            int tmp_sum = 0;
            for(int j=0; j<x; j++) {
                if(j==list.size()) {
                    break;
                }
                Node cur = list.get(j);
                tmp_sum += cur.num * cur.occurence;
            }

            res[i] = tmp_sum;
        }

        return res;
    }

    class Node implements Comparable<Node> {
        int num;
        int occurence;

        Node(int num, int occurence) {
            this.num = num;
            this.occurence = occurence;
        }

        @Override
        public int compareTo(Node cmp) {
            if(occurence==cmp.occurence) {
                return cmp.num - num;
            }
            return cmp.occurence - occurence;
        }
    }
}
