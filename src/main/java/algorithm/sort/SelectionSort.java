package algorithm.sort;

/**
 * <p>
 *
 * @description: 选择排序
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/16
 * @return: algorithm.sort.SelectionSort
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arg = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        int[] ints = selectionSort(arg);
        for (int anInt : ints) {
            System.out.print(anInt + "  ");
        }
    }

    /**
     * 选择排序，指针标记最小值，与第一个值进行交换
     * 时间复杂度永远是O(n2)
     * @param nums
     * @return
     */
    public static int[] selectionSort(int[] nums){
        int len = nums.length;
        for (int i = 0; i< len - 1; i++){
            int index = i;
            // for循环后，index应该是最小的值
            for (int j = i; j < len; j++){
                if (nums[j] < nums[index]){
                    index = j;
                }
            }
            int base = nums[i];
            nums[i] = nums[index];
            nums[index] = base;
        }
        return nums;
    }
}
