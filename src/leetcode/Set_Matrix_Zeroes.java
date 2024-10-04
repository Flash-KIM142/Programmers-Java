package leetcode;

import java.util.*;

public class Set_Matrix_Zeroes {

    public void setZeroes(int[][] matrix) {
        Set<Integer> zero_row = new HashSet<>();
        Set<Integer> zero_col = new HashSet<>();
        for(int r=0; r<matrix.length; r++) {
            for(int c=0; c<matrix[0].length; c++) {
                if(matrix[r][c]==0) {
                    zero_row.add(r);
                    zero_col.add(c);
                }
            }
        }

        /* fill 0 with rows */
        for(Integer r: zero_row) {
            Arrays.fill(matrix[r], 0);
        }
        /* fill 0 with cols */
        for(Integer c: zero_col) {
            for(int r=0; r<matrix.length; r++) {
                matrix[r][c] = 0;
            }
        }
    }
}
