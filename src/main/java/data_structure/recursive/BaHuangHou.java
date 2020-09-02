package data_structure.recursive;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: ZGF
 * 09-2020/9/1 : 16:12
 * context : 八皇后问题
 * 8*8 的网格，8个皇后分别不能在同一行和同一列，求有多少种解法
 */

/*
    1.第一个皇后放在第一行第一列
    2.第二个皇后放在第二行第一列，判断是否OK，如果不OK，继续放在第二列、第三列，依次把所有列放完，找到一个合适的
    3.继续第三个皇后，还是第一列、第二列，知道八个皇后都可以了，就成功了
    4.得到一个正确解时，再栈回退到上一个栈时，就开始回溯，即将第一个皇后放在第一列的所有正确解法全部得到
    5.回头继续将第一个皇后放在第二列，执行1，2，3，4步骤
 */
public class BaHuangHou {

    /**
     * 用一个一维的数组来表示八皇后和它们的位置
     * [0, 4, 7, 5, 2, 6, 1, 3]
     * 下标即代表了第几行，数字则代表了第几列
     */
    static int max = 8;
    static int[] arr = new int[max];

    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        fallson(0);
        System.out.println("总共有" + count.get() + "种解法");
    }

    /**
     * @param n  这里的n代表是行，意思是第几个皇后，从0开始
     */
    public static void fallson(int n){
        // 如果n==8了，说明前面0-7都放好了，超出了下标范围，代表完成了一次记录
        if (n == max) {
            printnow();
            return;
        }
        for (int i = 0; i < max; i++) {
            /* 先把这个皇后放在第一列，判断如果可以的话，就☆递归☆放下一个皇后，不可以的话，就放在遍历放在下一个位置 */
            /* 当第一种情况完成之后，就从第八个皇后开始回溯到第七个、第六个......第二个、第一个皇后，能走通就换一个皇后，依次回溯 */
            arr[n] = i;
            if (judge(n)) {     // 不冲突就换下一个皇后
                fallson(n + 1);
            }

            // 冲突了的话，就会遍历下一个，遍历完了还没有，就回溯上一个
        }
    }

    /**
     * 该方法是为了判断，后面落子的皇后，会不会跟前面的冲突
     * 如果冲突了则返回false
     * 如果不冲突则返回true
     */
    public static boolean judge(int n){
        for (int i = 0; i < n; i++) {
            /*
              * arr[i] == arr[n] 表示不在同一列
               * Math.abs(n - i) == Math.abs(arr[i] - arr[n])  表示不在同一条斜线，Math.abs是求取绝对值
                * */
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[i] - arr[n])){
                return false;
            }
        }
        return true;
    }

    public static void printnow(){
        count.incrementAndGet();
        for (int item : arr){
            System.out.print(item + "  ");
        }
        System.out.println();
    }
}
