package algorithm.array.s;

/**
 * @program: LeetCode Study
 * @description: 查询后的偶数和
 * @author: ZengGuangfu
 * @create 2019-06-06 10:27
 */

/**
 * 给出一个整数数组 A 和一个查询数组 queries。
   对于第 i 次查询，有 val = queries[i][0], index = queries[i][1]，我们会把 val 加到 A[index] 上。然后，第 i 次查询的答案是 A 中偶数值的和。
   （此处给定的 index = queries[i][1] 是从 0 开始的索引，每次查询都会永久修改数组 A。）
   返回所有查询的答案。你的答案应当以数组 answer 给出，answer[i] 为第 i 次查询的答案。

 示例：
     输入：A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
     输出：[8,6,2,4]
     解释：
     开始时，数组为 [1,2,3,4]。
     将 1 加到 A[0] 上之后，数组为 [2,2,3,4]，偶数值之和为 2 + 2 + 4 = 8。
     将 -3 加到 A[1] 上之后，数组为 [2,-1,3,4]，偶数值之和为 2 + 4 = 6。
     将 -4 加到 A[0] 上之后，数组为 [-2,-1,3,4]，偶数值之和为 -2 + 4 = 2。
     将 2 加到 A[3] 上之后，数组为 [-2,-1,3,6]，偶数值之和为 -2 + 6 = 4。
 */
public class SumEvenAfterQueries {
    public static void main(String[] args) {
        int A[] = {1,2,3,4};
        int[][] queries = {{1,0},{-3,1},{-4,0},{2,3}};
        int[] result = sumEvenAfterQueries3(A, queries);
        for (int num : result){
            System.out.print(num + "  ");
        }
    }

    /**
     * 执行用时 : 736 ms, 在Sum of Even Numbers After Queries的Java提交中击败了26.60% 的用户
       内存消耗 : 68.2 MB, 在Sum of Even Numbers After Queries的Java提交中击败了55.58% 的用户
     *
     * 一次通过，该题没什么好说的，就是替换原数组中的一个值，然后计算所有偶数的和就可以了。
     * TODO 个人觉得这样每次都要遍历数组的数据，找出其中的偶数，数组内容一多会降低效率，
     * TODO 下面更换一种方式去测试，先计算所有的偶数和。在值改变之前，判断被改变的数字是奇是偶，变后是奇是偶
     */
    public static int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] result = new int[queries.length];
        for (int i = 0 ; i < queries.length ; i++){
            int val = queries[i][0];
            int index = queries[i][1];
            A[index] = val + A[index];
            int sum = 0;
            for (int num : A){
                if ((num & 1) == 0){
                    sum += num;
                }
            }
            result[i] = sum;
        }
        return result;
    }


    /**
     * 执行用时 : 8 ms, 在Sum of Even Numbers After Queries的Java提交中击败了92.27% 的用户
       内存消耗 : 55.6 MB, 在Sum of Even Numbers After Queries的Java提交中击败了94.95% 的用户

       斗宗强者，恐怖如斯 ，执行用时从 727ms 编程了 8ms，妈耶。
       在下面我再简化一下步骤，if...else太多逼就装得不好了。
     */
    public static int[] sumEvenAfterQueries2(int[] A, int[][] queries) {
        int[] result = new int[queries.length];
        int sum = 0;
        for (int num : A){
            if ((num & 1) == 0){
                sum += num;
            }
        }
        for (int i = 0 ; i < queries.length ; i++){
            int val = queries[i][0];
            int index = queries[i][1];
            int before = A[index];
            A[index] = val + A[index];
            int after = A[index];
            if ((before & 1 ) == 1){
                if ( (after & 1) != 1 ){
                    sum += after;
                }
            }else{
                if ((after & 1) != 1){
                    sum = sum - before + after;
                }else{
                    sum -= before;
                }
            }
            result[i] = sum;
        }
        return result;
    }

    public static int[] sumEvenAfterQueries3(int[] A, int[][] queries) {
        int[] result = new int[queries.length];
        int sum = 0;
        for (int num : A){
            if ((num & 1) == 0){
                sum += num;
            }
        }
        for (int i = 0 ; i < queries.length ; i++){
            int val = queries[i][0];
            int index = queries[i][1];
            int before = A[index];
            A[index] = val + A[index];
            int after = A[index];
            // 直接判断前后是否为偶数，先减去变更前的，再加上变更后的
            if ((before & 1 ) != 1){
                sum -= before;
            }
            if ((after & 1) != 1){
                sum += after;
            }
            result[i] = sum;
        }
        return result;
    }
}
