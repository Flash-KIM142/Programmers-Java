import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PathFinding {
    static ArrayList<Node> nodeList = new ArrayList<>();
    static ArrayList<Integer> yList = new ArrayList<>();

    static int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][];
        Tree tree = new Tree();

        for (int i = 0; i < nodeinfo.length; i++) {
            nodeList.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
            if (yList.indexOf(nodeinfo[i][1]) < 0) yList.add(nodeinfo[i][1]);
        }
        Collections.sort(yList, Collections.reverseOrder());

        for (int y : yList) {
            int curY = y;
            for (Node node : nodeList)
                if (node.y == curY) tree.add(node.data, node.x, node.y);
        }

        tree.createPreorder(tree.root);
        tree.createPostorder(tree.root);

        answer[0] = tree.preorder.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = tree.postorder.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] nodeInfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] result = solution(nodeInfo);

        for (int data : result[0]) bfw.write(data + " ");
        bfw.newLine();
        for (int data : result[1]) bfw.write(data + " ");
        bfw.close();
    }

    static class Node {
        int data;
        int x;
        int y;
        Node left;
        Node right;

        Node(int data, int x, int y) {
            this.data = data;
            this.x = x;
            this.y = y;
        }
    }

    static class Tree {
        Node root;
        ArrayList<Integer> preorder = new ArrayList<>(), postorder = new ArrayList<>();

        void add(int data, int x, int y) {
            if (root == null) { // 루트가 아직 비어있으면
                root = new Node(data, x, y);
                root.left = null;
                root.right = null;
            } else search(null, root, data, x, y); // 들어갈 자리를 찾아주자
        }

        void search(Node parent, Node curNode, int data, int x, int y) {
            if (curNode == null) { //
                if (parent.x > x) parent.left = new Node(data, x, y);
                if (parent.x < x) parent.right = new Node(data, x, y);
            } else {
                if (curNode.x > x) search(curNode, curNode.left, data, x, y);
                if (curNode.x < x) search(curNode, curNode.right, data, x, y);
            }
        }

        void createPreorder(Node cur) { // root -> left -> right
            preorder.add(cur.data);
            if (cur.left != null) createPreorder(cur.left);
            if (cur.right != null) createPreorder(cur.right);
        }

        void createPostorder(Node cur) { // left -> right -> root
            if (cur.left != null) createPostorder(cur.left);
            if (cur.right != null) createPostorder(cur.right);
            postorder.add(cur.data);
        }
    }
}