package leetcode;

import java.util.*;

public class Reverse_Linked_List {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode result = reverseList(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    static ListNode reverseList(ListNode head) {
        Stack<ListNode> stk = new Stack<>();
        ListNode cur = head;
        while(cur!=null) {
            ListNode tmp = new ListNode(cur.val);
            stk.add(tmp);
            cur = cur.next;
        }

        ListNode res = stk.pop();
        cur = res;
        while(!stk.isEmpty()) {
            cur.next = stk.pop();
            cur = cur.next;
        }

        return res;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
