package com.aire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhuPeipei
 * @date 2021/11/15 22:25
 */
public class Exercise015 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = new Exercise015().threeSum(nums);
        System.out.println(res);
    }

    // 15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
                // 这里保证i不会有重复的数据
            }
            if (nums[i] > 0) {
                // 大于0了 后面不需要比较了
                break;
            }
            for (int j = i + 1; j < len - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                    // j不会有重复数据
                }
                boolean found = false;
                for (int m = len - 1; m > j; m--) {
                    int val = nums[i] + nums[j] + nums[m];
                    if (val < 0) {
                        break;
                    }
                    if (val == 0) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(-(nums[i] + nums[j]));
                        res.add(list);
                        found = true;
                        break;
                    }
                    // 这里不相等
                }
            }
        }
        return res;
    }
}
