package algorithm;

/**
 * <p>
 *
 * @description: 快速排序
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/16
 * @return: algorithm.QuickSort
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arg = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        int[] ints = sort(arg, 0, arg.length - 1);
        for (int anInt : ints) {
            System.out.print(anInt + "  ");
        }
    }

    public static int quickSort(int[] nums, int left, int right){
        int i = left;
        int j = right;
        int base = nums[i];
        while (i < j){
            // 反着找一轮
            while (i<j && nums[j] >= base){
                j--;
            }
            if (i<j){
                nums[i] = nums[j];
                i++;
            }
            // 正着找一轮
            while (i<j && nums[i] <= base){
                i++;
            }
            if (i<j){
                nums[j] = nums[i];
                j--;
            }
        }

        // 退出时，填坑
        nums[i] = base;
        return i;
    }

    // 分治
    public static int[] sort(int[] nums, int left, int right){
        if (left < right){
            int i = quickSort(nums, left, right);
            sort(nums, left, i-1);
            sort(nums, i + 1, right);
        }
        return nums;
    }
}
