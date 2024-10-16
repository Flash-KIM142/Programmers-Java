package leetcode;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int res = 0;

        while(l<r) {
            res = Integer.max(res, (r-l) * Integer.min(height[l], height[r]));

            if(height[l] < height[r]) {
                l++;
            }
            else {
                r--;
            }
        }

        return res;
    }
}
