package leetcode;

import java.util.*;

public class KthLargestPerfectSubtreeSizeInBinaryTree {

    public static void main(String[] args) {
        Integer[] nodes = {10,6,2,null,11,10,null};

        // Build and print the tree
        TreeNode root = buildTree(nodes);
        int res = kthLargestPerfectSubtree(root, 3);
        System.out.println(res);
    }

    public static int kthLargestPerfectSubtree(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        if(res.size()<k) {
            return -1;
        }
        Collections.sort(res);
        return res.get(res.size() - k);
    }

    public static void traverse(TreeNode root, List<Integer> res) {
        int tmp = judgePerfectBinaryTree(root);
        if(tmp!=-1) {
            res.add(tmp);
        }

        if(root.left!=null) {
            traverse(root.left, res);
        }
        if(root.right!=null) {
            traverse(root.right, res);
        }
    }

    public static int judgePerfectBinaryTree(TreeNode root) {
        if(root.left==null && root.right!=null) {
            return -1;
        }
        if(root.left!=null && root.right==null) {
            return -1;
        }

        Set<Integer> set = new HashSet<>();
        areLeavesOnSameLevel(root, set, 0);
        if(set.size()>=2 || set.contains(-1)) {
            return -1;
        }
        else if(set.size()==1) {
            int level = set.iterator().next() + 1;
            int size = 1;
            for(int i=0; i<level; i++) {
                size *= 2;
            }
            return size - 1;
        }
        else {
            return 1;
        }
    }

    public static void areLeavesOnSameLevel(TreeNode root, Set<Integer> levelSet, int level) {
        if(root.left==null && root.right!=null) {
            levelSet.add(-1);
            return;
        }
        if(root.left!=null && root.right==null) {
            levelSet.add(-1);
            return;
        }

        if(root.left!=null) {
            areLeavesOnSameLevel(root.left, levelSet, level+1);
        }
        if(root.right!=null) {
            areLeavesOnSameLevel(root.right, levelSet, level+1);
        }
        if(root.left==null && root.right==null) {
            levelSet.add(level);
        }
    }

    public static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0) return null;

        TreeNode root = new TreeNode(nodes[0]);  // Root node is the first element
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < nodes.length) {
            TreeNode current = queue.poll();

            // Add the left child
            if (nodes[i] != null) {
                current.left = new TreeNode(nodes[i]);
                queue.offer(current.left);
            }
            i++;

            // Add the right child
            if (i < nodes.length && nodes[i] != null) {
                current.right = new TreeNode(nodes[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
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
