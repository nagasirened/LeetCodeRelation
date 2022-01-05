package leetcode;

/**
 * 链表至少包含两个节点。
 链表中所有节点的值都是唯一的。
 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 不要从你的函数中返回任何结果。

 输入：head = [4,5,1,9], node = 1
 输出：[4,5,9]
 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.

 输入：head = [4,5,1,9], node = 5
 输出：[4,1,9]
 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 */
public class _237_删除链表中的节点 {

    /**
     * 将自己完全变成下一个节点，自己就消失了
     * @param node
     */
    public void deleteNode(ListNodeQQ node){
        node.val = node.next.val;
        node.next = node.next.next;
    }

}


class ListNodeQQ {
  int val;
  ListNode next;
    ListNodeQQ(int x) { val = x; }
}