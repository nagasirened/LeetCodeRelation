package renewal;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 输入: 1->1->2
 输出: 1->2

 示例 2:
 输入: 1->1->2->3->3
 输出: 1->2->3
 *
 */

public class _83_删除排序链表中的重复元素 {

    public Node83 deleteDuplicates(Node83 head) {
        if (head == null || head.next == null) return head;
        Node83 temp = head;
        while (temp.next != null) {
            if (temp.next.val == temp.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }
}

class Node83 {
    int val;
    Node83 next;
    Node83() {}
    Node83(int val) { this.val = val; }
    Node83(int val, Node83 next) { this.val = val; this.next = next; }
}
