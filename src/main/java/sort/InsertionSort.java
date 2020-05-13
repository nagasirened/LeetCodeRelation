package sort;

/**
 * @program: LeetCode Study
 * @description: 插入排序
 * @author: ZengGuangfu
 * @create 2019-08-13 13:18
 */
public class InsertionSort {


        public static void main(String[] args) {
            int array[]={12 , 1 , 3 , 46 , 5 , 0 ,-3 , 12 , 35 , 16 };

            for (int i = 1; i<array.length; i++){
                int temp = array[i];
                int j = i - 1;
                for (; j>=0 && array[j] > temp ;j--){
                    array[j+1] = array[j];
                }
                array[j+1] = temp;
            }

            for(int a:array){
                System.out.print(a + "  ");
            }

    }
}
