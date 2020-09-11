package algorithm.data_structure.Chains;

import java.util.Objects;
import java.util.Stack;

/**
 * author: ZGF
 * 05-2020/5/13 : 13:10
 * context : 单向链表
 */

public class OnewayLinkedList {

    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "公孙胜", "入云龙");
        HeroNode heroNode5 = new HeroNode(5, "关胜", "大刀");
        HeroNode heroNode6 = new HeroNode(6, "林冲", "豹子头");
        HeroNode heroNode7 = new HeroNode(7, "秦明", "霹雳火");
        SingleLinkedList sll1 = new SingleLinkedList();
        SingleLinkedList sll2 = new SingleLinkedList();
        sll1.add(heroNode1);sll1.add(heroNode4);sll1.add(heroNode7);
        sll2.add(heroNode2);sll2.add(heroNode3);sll2.add(heroNode5);sll2.add(heroNode6);


        SingleLinkedList merge = SingleLinkedList.merge(sll1, sll2);
        merge.list();
    }
}

class SingleLinkedList{

    /** 头结点 */
    private HeroNode head  = new HeroNode(0,  "", "");

    public HeroNode getHead() {
        return head;
    }

    SingleLinkedList(HeroNode head){
        this.head = head;
    }

    SingleLinkedList(){

    }
    /**
     * 不考虑顺序时，添加节点到单向链表,先想办法找到链表的最后一个节点last
     * 再将这个heroNode设置到last的next域中
     */
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            if (Objects.isNull(temp.next)){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 考虑排名时，需要判断一下HeroNode的no属性，比较大小进行排名;
     * 且如果有重复排名，就报错
     */
    public void sortAdd(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;  // 标识添加的编号是否存在，默认false，如果存在则会报错
        while (true){
            if (temp.next == null){  //说明temp已经在链表的最后了
                break;
            }
            if (temp.next.no > heroNode.no){  // 说明heroNode应该在 temp 和 temp.next之间
                break;
            }
            if (temp.next.no == heroNode.no){  // 说明这个编号已经存在了
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            System.out.printf("当前英雄排名已经存在，不能加入，当前编号是：%d", heroNode.no);
            System.out.println();
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }


    }

    /**
     * 显示链表
     */
    public void list(){
        System.out.println("------------分割线---------------");
        if (head.next == null){
            System.out.println("链表为null");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if (Objects.isNull(temp)){
                break;
            }
            System.out.println("节点信息是：" + temp.toString());
            temp = temp.next;
        }
    }

    /**
     * 修改节点的信息，no不能变化，name和nickName可以变化（根据新增节点的no修改即可）
     */
    public void update(HeroNode newNode){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;  // 表示是否找到该节点
        while (true){
            if (temp.no == newNode.no){
                flag = true;
                break;
            }
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        }else {
            System.out.printf("没有找到对应编号的英雄，编号是：%d", newNode.no);
        }
    }

    /**
     * 删除某个编号的节点，如果  temp.next.no = index，那么就是找到了，只需要令 temp.next = temp.next.next即可
     */
    public void remove(Integer index){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;   // 该标志表示是否找到了对应的
        while (true){
            if (temp.next == null){  // 表示找完了都没有找到
                break;
            }
            if (temp.next.no == index){   // 表示找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("没有找到这个排名[%d]的英雄，无法移除  \n", index);
        }
    }

    /**
     * fixme 以下是几个面试题
     * 1、求单链表中的节点的个数
     * 2、查找单链表中的倒数第k个
     * 3、单链表的反转  头节点不懂，其他有用的节点倒序
     * 4、从尾到头打印单链表【百度：方式一：反向遍历；方式2：stock栈】
     * 5、合并两个有序的单链表，合并之后依然有序
     */

    // 1、求单链表中的节点的个数
    public int length(){
        int length = 0;
        HeroNode temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 2、查找单链表中的倒数第k个
     * 首先遍历，获取所有的个数len， 倒数第k个，就是从第一个起，往后移动 len - k 次即可
     * 比如说：总共有是10个，导出第4个，也就是顺数第7个，从第一个开始往后移动  10 - 4 = 6次
     */
    public HeroNode findReverseIndex(int k){
        int len = this.length();
        if (len < k){
            return null;
        }else {
            HeroNode temp = head.next;
            for (int i = 0; i < len - k; i++) {
                temp = temp.next;
            }
            return temp;
        }
    }

    /**
     * 3.单链表的反转  头节点不懂，其他有用的节点倒序
     * 思路：先创建一个假的head，名为 temporary 临时节点作为一个假head。
     *      然后遍历数据，将后面的节点都插入到第一个，最后把第一个节点接入head即可
     */
    public void reversal(){
        if (head.next == null || head.next.next == null){
            System.out.println("链表为空或只有一个，不能反转");
            return;
        }
        HeroNode temporary = new HeroNode(0,  "", "");
        HeroNode temp = head.next;
        HeroNode nextOne;   // 指向temp的下一个节点，为了给temp赋值回来
        while (temp != null){
            nextOne = temp.next;
            temp.next = temporary.next;
            temporary.next = temp;

            temp = nextOne;
        }
        head.next = temporary.next;
    }

    /**
     * 4.从尾到头打印单链表【百度：方式一：反向遍历；方式2：stock栈】
     * 方式一：先反转再遍历打印，这样做的弊端时改变了原来的结构
     * 方式二：遍历压入栈内，通过栈先进后出的特性来实现
     */
    public void printByFalseReversal(){
        if (head.next ==  null){
            System.out.println("链表为空");
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp =  head.next;
        while (temp !=  null){
            stack.push(temp);
            temp = temp.next;
        }

        while (stack.size() > 0){
            System.out.println(stack.pop().toString());
        }
    }

    /**
     * 合并两个有序的单链表，合并之后依然有序：归并排序
     */
    public static SingleLinkedList merge(SingleLinkedList first, SingleLinkedList second){
        HeroNode node01 = first.head.next;
        HeroNode node02 = second.head.next;
        HeroNode result = new HeroNode(0, "", "");
        HeroNode node = result;
        while (node01 != null && node02 != null){
            if (node01.no <= node02.no){
                node.next = node01;
                node = node.next;
                node01 = node01.next;
            } else {
                node.next = node02;
                node = node.next;
                node02 = node02.next;
            }
        }
        if (node01 != null){
            node.next = node01;
        }
        if (node02 != null){
            node.next = node02;
        }
        return new SingleLinkedList(result);
    }

}