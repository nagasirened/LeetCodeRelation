package array;


/**
 * 二维数组的翻转
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 */
public class DigitalFlip {

    public static void main(String[] args) {
        int[][] A = {{1,1,0},{0,1,0},{0,0,0}};

        int[][] ints = flipAndInvertImage(A);
        for (int[] aa : ints) {
            for (int bb : aa){
                System.out.print(bb + "  ");
            }
        }
    }

    /**
     * 解答过程出错是因为中间没有复制A二维数组，导致改了前面的数会影响后面的数
     * 如先 让A[0][0]有值了，计算A[0][2]的时候是翻转过的值
     * 战胜了99%的老哥，撒了一泡高尿
     * @param A
     * @return
     */
    public static int[][] flipAndInvertImage(int[][] A) {
        int[][] B = new int[A.length][A[0].length];
        for(int i=0;i<A.length;i++){
            int len = A[i].length;
            for(int j = len-1; j >= 0;j--){
                int a = len - j - 1;
                B[i][a] = A[i][j] ^ 1;
            }
        }
        return B;
    }
}
