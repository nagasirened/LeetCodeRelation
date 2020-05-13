package data_structure.Chains;

import lombok.Data;

/**
 * author: ZGF
 * 05-2020/5/13 : 13:10
 * context : 单向链表的节点：英雄节点（水浒排序）
 */

@Data
public class HeroNode {
    /** 排名 */
    private Integer no;
    /** 名字 */
    private String name;
    /** 称号 */
    private String nickName;
    /** 下一个节点 */
    private HeroNode next;

    public HeroNode(Integer no, String name, String nickName){
        this.no =  no;
        this.name =  name;
        this.nickName =  nickName;
    }
}
