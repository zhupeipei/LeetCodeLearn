package com.aire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhuPeipei
 * @date 2021/11/26 22:06
 */
public class Exercise015_1 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> list = new Exercise015_1().threeSum(nums);
        for (List<Integer> ll : list) {
            System.out.println(ll.get(0) + ", " + ll.get(1) + ", " + ll.get(2));
        }
    }

    // 15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int num1 = nums[i];
            int m = len - 1;
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int num2 = nums[j];
                for (; m > j; m--) {
                    int num3 = nums[m];
                    int target = num1 + num2 + num3;
                    if (target == 0) {
                        res.add(new ArrayList<Integer>() {
                            {
                                add(num1);
                                add(num2);
                                add(num3);
                            }
                        });
                        break;
                    } else if (target > 0) {
                        continue;
                    } else {
                        m = Math.min(++m, len - 1);
                        break;
                    }
                }
            }
        }
        return res;
    }
}
