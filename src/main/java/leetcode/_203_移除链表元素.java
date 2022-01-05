package leetcode;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:

 输入: 1->2->6->3->4->5->6, val = 6
 输出: 1->2->3->4->5
 */
public class _203_移除链表元素 {

    public Node203 removeElements(Node203 head, int val) {
        // 增加一个虚拟的头节点
        Node203 base = new Node203(-1, head);
        // 不要直接操作base，会导致找不回来base的内容，用另外一个temp操作
        Node203 temp = base;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return base.next;
    }
}

class Node203 {
    int val;
    Node203 next;
    Node203() {}
    Node203(int val) { this.val = val; }
    Node203(int val, Node203 next) { this.val = val; this.next = next; }
}