package array.s;


/**
 * @program: LeetCode Study
 * @description: 种花问题
 * @author: ZengGuangfu
 * @create 2019-08-05 14:53
 *
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
   给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？
    能则返回True，不能则返回False。

    示例 1:
    输入: flowerbed = [1,0,0,0,1], n = 1
    输出: True

    示例 2:
    输入: flowerbed = [1,0,0,0,1], n = 2
    输出: False
 */
public class CanPlaceFlowers {

    public static void main(String[] args) {
        int[] a = {0};
        canPlaceFlowers(a, 1);
    }

    /**
     * 这个题主要是注意首尾的0，两个0即可增加一个可种植的树。我可以在最左边添加一个0，保证所有1 出现之前的0算法一致。
     * 尾巴位置的0的尾数则稍微不一样
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n){
        int count = 1;   // 对零的计数，初始化为1相当于在最前面加一个0
        int result = 0;
        for (int i = 0 ; i< flowerbed.length; i++){
            if (flowerbed[i] == 1){
                result += (count - 1)/2;
                count = 0;
            }else{
                count++;
            }
        }
        result += count/2;   // 最尾巴位置的0，两个就可以种一颗
        return result >= n;
    }
}
