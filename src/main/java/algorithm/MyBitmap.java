package algorithm;

import org.apache.commons.lang3.time.StopWatch;

import java.util.BitSet;

/**
 * @program: mall
 * @description:
 * @author: ZengGuangfu
 * @create 2019-09-06 15:42
 */
public class MyBitmap {

    private long[] words;
    private int size;

    public MyBitmap(int size) {
        this.size = size;
        this.words = new long[getWordIndex(size - 1) + 1];
    }

    /**
     * 定位Bitmap某一位所对应的word
     * 移动6为是因为 long = 2 ^ 6 =64 bit
     *
     * @param bitIndex
     * @return
     */
    private int getWordIndex(int bitIndex) {
        int i = bitIndex >> 6;
        System.out.println(i);
        return i;

    }

    /**
     * 把Bitmap某一位设置为true
     *
     * @param bitIndex
     */
    public void setBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1L << bitIndex);
    }

    /**
     * 判断Bitmap某一位的状态
     *
     * @param bitIndex
     * @return
     */
    public boolean getBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap有效范围");
        }

        int wordIndex = getWordIndex(bitIndex);
        System.out.println("------------   "+words[wordIndex]);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        MyBitmap myBitmap = new MyBitmap(30000000);
        System.out.println("");
        myBitmap.setBit(1260000);
        myBitmap.setBit(7500);
        System.out.println(myBitmap.getBit(1260000));
        System.out.println("7501：" + myBitmap.getBit(7501));

        System.out.println("==================");
        //Java的实现类
        BitSet bitSet = new BitSet(30000000);
        bitSet.set(1260000);
        bitSet.set(7500);
        System.out.println(bitSet.get(1260000));
        System.out.println(bitSet.get(7800));

        sw.stop();
        System.out.println(sw.getNanoTime());
        // 128size  1.4ms
        // 30000000size  3ms
    }
}
