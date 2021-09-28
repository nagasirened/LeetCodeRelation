package _2021;

public class _002M两数相加 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println();
    }

    /**
     * 输入：l1 = [2,4,3], l2 = [5,6,4] 输出：[7,0,8] 解释：342 + 465 = 807.
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode tmp = pre;
        int jin = 0;
        while (l1 != null || l2 != null) {
            int cnt;
            if (l1 == null) {
                cnt = l2.val;
                l2 = l2.next;
            } else if (l2 == null) {
                cnt = l1.val;
                l1 = l1.next;
            } else {
                cnt = l1.val + l2.val;
                l1 = l1.next;
                l2 = l2.next;
            }
            tmp.next = new ListNode((cnt + jin) % 10);
            jin = (cnt + jin) / 10;
            tmp = tmp.next;
        }

        if (jin != 0) {
            tmp.next = new ListNode(jin);
        }

        return pre.next;
    }
}