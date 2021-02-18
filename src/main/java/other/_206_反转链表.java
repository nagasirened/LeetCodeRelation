package other;

/**
   输入: 5->4->3->2->1->NULL
   输出: 1->2->3->4->5->NULL
 */

public class _206_反转链表 {

    // 递归的方式
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 递归到最后一个的时候，直接返回了1    head.next = 1,他的next要等于
        /**
         * 数到2的时候，head=2，head.next=1,
         * 我们将head.next.next(1) 的值指为2即可，
         * 同时将2的next指为null
         */
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 非递归的方式
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 先创建一个指针newHead，下面的替换步骤，相当于把元素移动到了newHead下面
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}


class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
