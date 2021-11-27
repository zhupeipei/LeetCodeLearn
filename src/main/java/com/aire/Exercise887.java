package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/11/27 11:51
 */
public class Exercise887 {
    public static void main(String[] args) {
        int res = new Exercise887().superEggDrop(2, 100);
        System.out.println(res);
    }

    // 887. 鸡蛋掉落
    // s(k,f)表示k个蛋，f步能走的最大的层数
    // s(1,f)=f
    // s(2,f)=s(1,f)
    // s(k,f) 这个想不通
    public int superEggDrop(int k, int n) {
//        return dp(k, n);
        return 1;
    }
}
