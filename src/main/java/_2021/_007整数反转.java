package _2021;

public class _007整数反转 {

    public static void main(String[] args) {
        System.out.println(reverse(-60));
    }

    public static int reverse(int x) {
        int base = 0;
        while (x != 0) {
            int temp = x % 10;
            // badCase
            if (base>214748364 || (base==214748364 && temp>7)) { return 0; }
            if (base<-214748364 || (base==-214748364 && temp<-8)) { return 0; }
            base = base * 10 + temp;
            x /= 10;
        }
        return base;
    }
}
