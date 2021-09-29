package _2021;

public class _067二进制求和 {
    public static void main(String[] args) {
        System.out.println(addBinary("1010", "1111"));
    }

    /**
     *   1 0 1 0
     *   1 1 1 1
     *   1 1 0 0 1
     */
    public static String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int l1 = a.length() - 1;
        int l2 = b.length() - 1;
        int tmp = 0;
        while (l1 >= 0 || l2 >= 0) {
            // '1' - '0' = 1
            int la = l1 >= 0 ? a.charAt(l1) - '0' : 0;
            int lb = l2 >= 0 ? b.charAt(l2) - '0' : 0;
            int sum = la + lb + tmp;
            builder.append(sum % 2);
            tmp = sum / 2;

            l1--;
            l2--;
        }

        if (tmp>0) {
            builder.append(tmp);
        }
        return builder.reverse().toString();
    }
}
