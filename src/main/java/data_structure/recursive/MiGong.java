package data_structure.recursive;

/**
 * author: ZGF
 * 09-2020/9/1 : 14:28
 * context :
 */

public class MiGong {

    public static void main(String[] args) {
        int[][] migong = generate();
        boolean result = findLoad(migong, 1, 1);
        System.out.println(result);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(migong[i][j] + "  ");
            }
            System.out.println();
        }
    }

    // 模拟一个迷宫，四周都是墙，且3,1 和 3,2也是墙 如下
        /***给小球找路，从1,1 递归回溯到6,5 */
        /*
        1  1  1  1  1  1  1
        1  0  0  0  0  0  1
        1  0  0  0  0  0  1
        1  1  1  0  0  0  1
        1  0  0  0  0  0  1
        1  0  0  0  0  0  1
        1  0  0  0  0  0  1
        1  1  1  1  1  1  1
         */
    public static int[][] generate(){

        int[][] migong = new int[8][7];
        for (int i = 0; i < 7; i++) {
            migong[0][i] = 1;
            migong[7][i] = 1;
        }
        for (int i = 1; i < 7; i++) {
            migong[i][0] = 1;
            migong[i][6] = 1;
        }
        migong[3][1] = 1;
        migong[3][2] = 1;

        System.out.println("打印出迷宫的模样");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(migong[i][j] + "  ");
            }
            System.out.println();
        }
        return migong;
    }

    /**
     * 给小球找路，找到则为true
     * @param migong  表示地图，迷宫
     * @param i，j  表示开始的位置
     *         约定migong[i][j] = 0是表示该小球还没有走过
     *         2表示墙
     *         2表示通路0
     *         3表示已经走过，但是不通
     *         ☆☆☆☆   约定策略  下->右->上->左，如果改点走不通再回溯  ☆☆☆☆
     * @return
     */
    public static boolean findLoad(int[][] migong, int i, int j) {
        if (migong[6][5] == 2) {
            return true;
        } else {
            if (migong[i][j] == 0) { // 如果这个点还没有走过，按策略走，先假定该点可以走通
                migong[i][j] = 2;
                if (findLoad(migong, i+1, j )){  // 下
                    return true;
                }else if (findLoad(migong, i, j+1 )){  // 右
                    return true;
                }else if (findLoad(migong, i-1, j )){  // 上
                    return true;
                }else if (findLoad(migong, i, j-1 )){  // 左
                    return true;
                }else {
                    migong[i][j] = 3;  // 表示此路不通
                    return false;
                }
            }else {
                // 不等于0，代表只能是1，2，3
                return false;
            }
        }
    }
}
