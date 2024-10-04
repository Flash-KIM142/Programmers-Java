package leetcode;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int node_num_of_cur_level = q.size();
            List<Integer> tmp = new ArrayList<>();

            for (int i = 0; i < node_num_of_cur_level; i++) {
                TreeNode cur_node = q.poll();
                if (cur_node.left != null) {
                    q.add(cur_node.left);
                }
                if (cur_node.right != null) {
                    q.add(cur_node.right);
                }
                tmp.add(cur_node.val);
            }
            res.add(tmp);
        }

        return res;
    }

    public class TreeNode {
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
