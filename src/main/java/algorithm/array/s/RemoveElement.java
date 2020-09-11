package algorithm.array.s;

/**
 * @program: LeetCode Study
 * @description: 移除元素
 * @author: ZengGuangfu
 * @create 2019-06-06 18:59
 */

/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

 示例 1:
    给定 nums = [3,2,2,3], val = 3,
    函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
    你不需要考虑数组中超出新长度后面的元素。
 示例 2:
    给定 nums = [0,1,2,2,3,0,4,2], val = 2,
    函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
    注意这五个元素可为任意顺序。
    你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElement {
    public static void main(String[] args) {
//        int[] nums = {0,1,2,2,3,0,4,2};
        int[] nums = {3,2,2,3};
        int resule = removeElement(nums, 2);
        System.out.println(resule);
    }

    public static int removeElement(int[] nums, int val) {
       /*
        想简单了，以为只要返回数字就行了，结果人家还要把数据往迁移，再来
        int leng = nums.length;
        for ( int i=0; i< nums.length;i++){
            if (nums[i] == val){
                leng--;
            }
        }
        return leng;*/

        /**
         * 执行用时 : 1 ms, 在Remove Element的Java提交中击败了99.56% 的用户
         内存消耗 : 35.3 MB, 在Remove Element的Java提交中击败了84.44% 的用户

         双指针，前面的val交互后面的非val
         */
       int start = 0,end = nums.length - 1;
       while( start < end ){
           if (nums[start] == val){
               if (nums[end] != val){
                   int temp = nums[start];
                   nums[start] = nums[end];
                   nums[end] = temp;
               }else {
                   end --;
               }
           }else {
                start++;
           }
        }

        int leng = nums.length;
        for ( int i=0; i< nums.length;i++){
            if (nums[i] == val){
                leng--;
            }
        }
        return leng;
    }
}
