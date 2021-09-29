package _2021;

public class _005最长回文子串_中 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    // 中心拓展法
    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        // 字符串为偶数，则在最中间位置加一个 # 作为中心
        StringBuilder builder = new StringBuilder("#");
        for (int i = 0; i < length; i++) {
            builder.append(s.charAt(i)).append("#");
        }
        s = builder.toString();
        length = s.length();
        int index = 0;
        int diffuseBase = 0;
        for (int i = 0; i < length; i++) {
            int diffuse = subpalidromelen(s, i, length);
            if (diffuse > diffuseBase) {
                diffuseBase = diffuse;
                index = i;
            }
        }
        String pre = s.substring(index - diffuseBase, index + diffuseBase + 1);
        return pre.replaceAll("#", "");
    }

    private static int subpalidromelen(String s, int i, int length) {
        int diffuse = 1;
        while ((i - diffuse) >= 0 && (i + diffuse) < length) {
            if (s.charAt(i - diffuse) == s.charAt(i + diffuse)) {
                diffuse ++;
            } else {
                break;
            }
        }
        diffuse--;
        return diffuse;
    }

    // 中心拓展


}
