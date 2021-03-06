package algorithm.array.s;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: LeetCode Study
 * @description: 查找常用字符
 * @author: ZengGuangfu
 * @create 2019-06-04 09:02
 *
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * 你可以按任意顺序返回答案。
 *
 示例 1：
    输入：["bella","label","roller"]
    输出：["e","l","l"]

 示例 2：
    输入：["cool","lock","cook"]
    输出：["c","o"]

 提示：
    1 <= A.length <= 100
    1 <= A[i].length <= 100
    A[i][j] 是小写字母
 */
public class CommonChars {
    public static void main(String[] args) {
        String[] A = {"bella","label","roller"};
        List<String> stringList = commonChars(A);
    }

    /**
     * 取第一个字符串，取出所有的字符，将字符在其它字符串中匹配，如果包含就剃出来加入List集合
     */
    public static List<String> commonChars(String[] A) {
        ArrayList<String> list = new ArrayList<String>();
        if (A == null) return list;
        // 最外层，遍历第一个字符串
        for(int i = 0 ; i < A[0].length() ; i++){
            boolean flag = true;
            String s = String.valueOf(A[0].charAt(i));
            for (int j = 1 ; j< A.length ; j++){
                if (A[j].indexOf(s) > -1){
                    A[j].replaceFirst(s,"");
                }else{
                    flag = false;
                    break;
                }
            }
            if (flag){
                list.add(s);
            }
        }
        return list;
    }
}
