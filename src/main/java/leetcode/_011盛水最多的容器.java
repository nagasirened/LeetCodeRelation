package leetcode;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 底 * 高
 */
public class _011盛水最多的容器 {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    /**
     * 用类似于 n^2 的时间复杂度(冒泡)能解决，但是不用在这儿
     * 双指针，指针1在头，指针2在尾，向内收缩，收缩数字小的那一个
     */
    public static int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int head = 0;
        int tail = height.length - 1;
        int size = 0;
        while (head < tail) {
            int headH = height[head];
            int tailH = height[tail];
            size = Math.max((tail - head) * Math.min(headH, tailH), size);
            if (headH < tailH) {
                head++;
            } else {
                tail--;
            }
        }
        return size;
    }
}
