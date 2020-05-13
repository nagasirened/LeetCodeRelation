package data_structure;

import java.io.*;

/**
 * author: ZGF
 * 05-2020/5/8 : 7:47
 * context : 稀疏数组
 * discribtion:
 1、稀疏算法的基本介绍
    当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。从而减少计算机不必要的内存开销。

 2、稀疏算法的处理方式
    （1）数组中第一行记录原始数组中一共有几行几列，有多少个不同的值。
    （2）把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模。
 */

public class SparseArray {

    public static void main(String[] args) throws IOException {
        // 1.初始化一个二维数组， 11*11 的棋盘， 且有两个子，第一个是黑子，位置在第二行第三列；  第二个是白子，位置在第三行第四列 (没有子的地方默认存储0)
        int[][] primaryArray = new int[11][11];
        primaryArray[1][2] = 1;
        primaryArray[2][3] = 2;
        for (int[] array : primaryArray) {
            for (int num : array) {
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }

        // 2.将这个二维数组转为稀疏数组  第一步要先知道有多少个数据，才能知道稀疏数组有多少行. 稀疏数组固定三列
        int sum = 0;
        for (int[] array : primaryArray) {
            for (int num : array) {
                if (num != 0){
                    sum++;
                }
            }
        }

        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        int index = 1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (primaryArray[i][j] != 0){
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = primaryArray[i][j];
                    index++;
                }
            }
        }

        // 将稀疏数组存入磁盘中
        /** 存入了之后长这样
         * 11	11	2
            1	2	1
            2	3	2
         */
        File file = new File("D://sparseArray.data");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for (int[] ints: sparseArray) {
                for (int num : ints) {
                    fileWriter.write(num + "\t");
                }
                fileWriter.write("\r\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fileWriter != null){
                try {
                    fileWriter.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        // 从磁盘中解析文件为稀疏数组
        FileReader fileReader = new FileReader("D://sparseArray.data");
        BufferedReader in = new BufferedReader(fileReader);
        String line; //  代表一行数据
        int row = 0;
        int[][] sparseArrayNew = null;
        while ((line = in.readLine()) != null){
            String[] items = line.split("\t");
            if (row == 0) {
                sparseArrayNew = new int[Integer.valueOf(items[2] + 1)][3];
            }
            sparseArrayNew[row][0] = Integer.valueOf(items[0]);
            sparseArrayNew[row][1] = Integer.valueOf(items[1]);
            sparseArrayNew[row][2] = Integer.valueOf(items[2]);
            row++;
        }

        // 将稀疏数组转为二维数组
        int[][] primaryArrayNew = new int[sparseArrayNew[0][0]][sparseArrayNew[0][1]];
        for (int i = 1; i < sparseArrayNew.length; i++) {
            primaryArrayNew[sparseArrayNew[i][0]][sparseArrayNew[i][1]] = sparseArrayNew[i][2];
        }
        // 打印一下
        System.out.println("华丽丽的分割线");
        for (int[] array : primaryArrayNew) {
            for (int num : array) {
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }
    }

}
