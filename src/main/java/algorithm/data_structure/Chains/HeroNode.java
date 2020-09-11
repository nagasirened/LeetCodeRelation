package algorithm.data_structure.Chains;

/**
 * author: ZGF
 * 05-2020/5/13 : 13:10
 * context : 单向链表的节点：英雄节点（水浒排序）
 */

public class HeroNode {
    /** 排名 */
    public Integer no;
    /** 名字 */
    public String name;
    /** 称号 */
    public String nickName;
    /** 下一个节点 */
    public HeroNode next;

    public HeroNode(Integer no, String name, String nickName){
        this.no =  no;
        this.name =  name;
        this.nickName =  nickName;
    }

    public String toString(){
        return "HeroNode no=["  + no + "],name=[" + name + "],nickName=[" + nickName + "]";
    }
}
