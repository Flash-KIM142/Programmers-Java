package leetcode;

import java.util.*;

public class KthLargestPerfectSubtreeSizeInBinaryTree {

    public static int kthLargestPerfectSubtree(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        if(res.size()<k) {
            return -1;
        }
        Collections.sort(res);
        int answer = 1 << res.get(res.size() - k);
        return answer - 1;
    }

    public static int traverse(TreeNode root, List<Integer> res) {
        if(root==null) {
            return 0;
        }
        int left = traverse(root.left, res);
        int right = traverse(root.right, res);
        if(left==right && left>=0) {
            res.add(left+1);
            return left+1;
        }
        return -1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
