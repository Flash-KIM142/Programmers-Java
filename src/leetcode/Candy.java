package leetcode;

public class Candy {

    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        for(int i=0; i<ratings.length; i++) {
            candy[i] = 1;
        }
        for(int i=1; i<ratings.length; i++) {
            if(ratings[i] > ratings[i-1]) {
                candy[i] = candy[i-1]+1;
            }
        }
        for(int i=ratings.length-2; i>=0; i--) {
            if(ratings[i] > ratings[i+1]) {
                candy[i] = Integer.max(candy[i], candy[i+1]+1);
            }
        }

        int sum = 0;
        for(int i=0; i<candy.length; i++) {
            sum += candy[i];
        }
        return sum;
    }
}
