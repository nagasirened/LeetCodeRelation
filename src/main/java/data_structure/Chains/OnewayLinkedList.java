package data_structure.Chains;

import java.util.Objects;

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

        SingleLinkedList sll = new SingleLinkedList();
        sll.sortAdd(heroNode1);
        sll.sortAdd(heroNode4);
        sll.sortAdd(heroNode3);
        sll.sortAdd(heroNode2);
        sll.list();

        System.out.println("------------分割线---------------");
        HeroNode heroNode5 = new HeroNode(4, "关胜", "大刀");
        sll.update(heroNode5);
        sll.list();
    }
}

class SingleLinkedList{

    /** 头结点 */
    private HeroNode head  = new HeroNode(0,  "", "");

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
}