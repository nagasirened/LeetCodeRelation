package array.s;

/**
 * @program: LeetCode Study
 * @description: 合并两个有序数组
 * @author: ZengGuangfu
 * @create 2019-09-12 13:55
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 *
    说明:
    初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
    你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
    示例:
    输入:
    nums1 = [1,2,3,0,0,0], m = 3
    nums2 = [2,5,6],       n = 3
    输出: [1,2,2,3,5,6]
 */

public class Merge {

    public static void main(String[] args) {

    }

    /**
     * 第一次看题，感觉像是以前写过的那种插入排序，不过我需要新建一个数组，而不是使用原数组m进行数据保存
     * 但是看题意，似乎是说不新建对象，而是将nums2 插入到 nums1中
     * m 代表 nums1的长度     n则是nums2的长度
     *
     * 执行用时 :1 ms, 在所有 Java 提交中击败了97.02%的用户
     内存消耗 :36.1 MB, 在所有 Java 提交中击败了85.07%的用户
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 倒起来，反过来弄就成了噻
        int p = m + n -1;
        while(m > 0 && n > 0){
            if (nums1[m-1] >= nums2[n-1]){
                nums1[p] = nums1[m-1];
                m--;
            }else{
                nums1[p] = nums2[n-1];
                n--;
            }
            p--;
        }

        // 仅剩 nums2的数了,仅剩nums1的话不用管啊
        while (n > 0){
            nums1[p] = nums2[n-1];
            p--;n--;
        }
    }

    // 跟上面一样，只是写法更简单了而已
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // 倒起来，反过来弄就成了噻
        int p = m + n -1;
        while(m > 0 && n > 0){
            nums1[p--] = (nums1[m-1] >= nums2[n-1] ? nums1[m-- -1] : nums2[n-- -1]);
        }

        // 仅剩 nums2的数了,仅剩nums2的数不用管啊
        while (n > 0){
            nums1[p--] = nums2[n-- -1];
        }
    }
}
