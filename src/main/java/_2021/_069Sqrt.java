package _2021;

public class _069Sqrt {

    public static void main(String[] args) {
        System.out.println(new _069Sqrt().mySqrt(6));
    }

    /**
     * 二分查找法
     */
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = (r + l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}

