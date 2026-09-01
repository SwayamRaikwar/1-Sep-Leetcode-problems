/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        // Dummy heads for less and greater lists
        ListNode lessHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);

        // Pointers to build the lists
        ListNode less = lessHead;
        ListNode greater = greaterHead;

        // Traverse the original list
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                less.next = curr;
                less = less.next;
            } else {
                greater.next = curr;
                greater = greater.next;
            }
            curr = curr.next;
        }

        // Avoid cycle in linked list
        greater.next = null;

        // Connect less list with greater list
        less.next = greaterHead.next;

        return lessHead.next;
    }
}
