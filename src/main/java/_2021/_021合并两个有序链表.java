package _2021;

public class _021合并两个有序链表 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(5)));
        ListNode listNode = mergeTwoLists(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode base = pre;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                base.next = l2;
                l2 = l2.next;
            } else {
                base.next = l1;
                l1 = l1.next;
            }
            base = base.next;
            base.next = null;
        }

        if (l1 != null) {
            base.next = l1;
        }
        if (l2 != null) {
            base.next = l2;
        }

        return pre.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
