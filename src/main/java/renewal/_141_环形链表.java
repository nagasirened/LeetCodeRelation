package renewal;

/**
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 */
public class _141_环形链表 {


    /**
     * 使用快慢指针 如果有环，快慢两指针一定会相遇
     */
    public boolean hasCycle(Node3 head) {
        if (head == null || head.next == null)
        return true;

        Node3 slow = head;
        Node3 fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}


class Node3 {
    int val;
    Node3 next;
    Node3(int x) {
        val = x;
        next = null;
    }
}