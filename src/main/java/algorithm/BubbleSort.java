package algorithm;

/**
 * <p>
 *
 * @description: 冒泡排序
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/16
 * @return: algorithm.BubbleSort
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arg = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        int[] ints = bubbleSort(arg);
        for (int anInt : ints) {
            System.out.print(anInt + "  ");
        }
    }

    /**
     * 冒泡排序，两两交换
     * @param nums
     * @return
     */
    public static int[] bubbleSort(int[] nums){
        int len = nums.length;
        for (int i = 0; i < len ; i++){
            for (int j = 0; j < len - 1 - i; j++){
                if (nums[j] > nums[j+1]){
                    int base = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = base;
                }
            }
        }
        return nums;
    }
}
