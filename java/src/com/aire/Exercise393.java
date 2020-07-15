package com.aire;

public class Exercise393 {
    public static void main(String[] args) {
        System.out.println(new Exercise393().validUtf8(new int[]{197, 130, 1}));
    }
    /////////
    // 这个问题有个坑在于 可能数组里有多个utf-8数据
    /////////

    /**
     * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
     * <p>
     * 对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
     * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
     * 这是 UTF-8 编码的工作方式：
     * <p>
     * Char. number range  |        UTF-8 octet sequence
     * (hexadecimal)    |              (binary)
     * --------------------+---------------------------------------------
     * 0000 0000-0000 007F | 0xxxxxxx
     * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
     * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
     * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
     * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/utf-8-validation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean validUtf8(int[] data) {
        if (data == null) {
            return false;
        }
        int index = 0;
        while (true) {
            if (index >= data.length) {
                return true;
            }
            int data1 = data[index];
            int len = -1;
            int size = 7;
            for (int i = 0; i < 8; i++) {
                if ((data1 & (1 << size)) != (1 << size)) {
                    break;
                }
                len = i;
                size--;
            }
            if (len == -1) { // 说明高位为0
                index++;
                continue;
            }
            if (len + 1 >= 5) {
                return false;
            }
            if (len + 1 == 1) {
                return false;
            }
            int inx = index + 1;
            for (int i = inx; i < inx + len; i++) {
                if (i >= data.length) {
                    return false;
                }
                if ((data[i] & (1 << 7)) == (1 << 7)) {
                    continue;
                } else {
                    return false;
                }
            }
            index = index + len + 1;
        }
    }
}
