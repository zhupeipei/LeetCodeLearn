package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/12/3 13:42
 */
public class Exercise238 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] outputs = new Exercise238().productExceptSelf(nums);
        for (int i = 0; i < outputs.length; i++) {
            System.out.print(outputs[i] + ", ");
        }
    }

    // 238. 除自身以外数组的乘积
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];

        output[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        int R = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] = output[i] * R;
            R *= nums[i];
        }

        return output;
    }
}
