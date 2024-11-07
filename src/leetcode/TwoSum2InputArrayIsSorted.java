package leetcode;

public class TwoSum2InputArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while(true) {
            if(numbers[l] + numbers[r] == target) {
                break;
            }

            if(numbers[l] + numbers[r] > target) {
                r--;
            }
            else {
                l++;
            }
        }

        return new int[]{l+1, r+1};
    }
}
