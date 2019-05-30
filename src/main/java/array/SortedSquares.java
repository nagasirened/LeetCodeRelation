package array;


import java.util.Arrays;

/**
 * 有序数组的平方
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * 输入：[-4,-1,0,3,10]
 输出：[0,1,9,16,100]
 */
public class SortedSquares {

    public static void main(String[] args) {
        int[] A = {-4,-1,0,3,10};
        int[] ints = sortedSquares2(A);
        for (int a : ints){
            System.out.print(a + "  ");
        }

    }

    public static int[] sortedSquares(int[] A) {
        for (int i=0 ; i < A.length-1 ; i++) {
            for (int j = i+1 ; j<A.length ; j++){
                int a = A[i];
                if (Math.pow(A[i],2) > Math.pow(A[j],2) ){
                    A[i] = A[j];
                    A[j] = a;
                }
            }
        }

        for ( int a= 0; a<A.length;a++ ) {
            A[a] = (int)Math.pow(A[a],2);
        }


        return A;
    }

    public static int[] sortedSquares2(int[] A) {
        for (int i=0 ; i < A.length ; i++) {
            A[i] = (int)Math.pow(A[i],2);
        }
        Arrays.sort(A);
        return A;
    }
}
