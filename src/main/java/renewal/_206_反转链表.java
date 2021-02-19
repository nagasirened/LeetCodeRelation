package renewal;

/**
   输入: 5->4->3->2->1->NULL
   输出: 1->2->3->4->5->NULL
 */

public class _206_反转链表 {

    // 递归的方式
    public Node2 reverseList(Node2 head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 递归到最后一个的时候，直接返回了1    head.next = 1,他的next要等于
        /**
         * 数到2的时候，head=2，head.next=1,
         * 我们将head.next.next(1) 的值指为2即可，
         * 同时将2的next指为null
         */
        Node2 newHead = reverseList(head.next);
        head.next.next = head;  // 4.next = 5
        head.next = null;       // 5.next = null
        return newHead;
    }

    // 非递归的方式
    public Node2 reverseList2(Node2 head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 先创建一个指针newHead，下面的替换步骤，相当于把元素移动到了newHead下面
        Node2 newHead = null;
        while (head != null) {
            /**
             * 原始：head-5-4-3-2-1
             *      newHead-null
             *
             * 1、先让temp 等于4-3-2-1
             * 2、让5.next 指向newHead此时的null
             * 3、让newHead = 5，此时5.next=null
             * 4、head指向temp  就相当于将head后面链表的第一个元素给了newHead
             *
             * 后续：head 4-3-2-1
             *      newHead-5-null
             */
            Node2 temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}


class Node2 {
    int val;
    Node2 next;
    Node2() {}
    Node2(int val) { this.val = val; }
    Node2(int val, Node2 next) { this.val = val; this.next = next; }
}
