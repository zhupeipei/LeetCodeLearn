package com.aire;

/**
 * @author ZhuPeipei
 * @date 2021/12/3 10:36
 */
public class Exercise240 {
    public static void main(String[] args) {
//        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
//        int target = 0;
        int[][] matrix = {{2, 5}, {2, 8}, {7, 9}, {7, 11}, {9, 11}};
        int target = 7;
        System.out.println(new Exercise240().searchMatrix(matrix, target));
    }

    // 240. 搜索二维矩阵 II
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x >= 0 && x < m && y >= 0 && y < n) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }

    private boolean binarySearch(int[][] matrix, int col, int row1, int row2, int target) {
        int[] nums = matrix[col];
        int left = row1, right = row2;
        if (target > nums[right]) {
            return false;
        }
        if (target < nums[left]) {
            return false;
        }
        int middle = 0;
        while (left < right) {
            middle = left + ((right - left) >> 2);
            if (target == nums[middle]) {
                return true;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return nums[left] == target;
    }

    private boolean binarySearchVertical(int[][] matrix, int row, int target) {
        int left = 0, right = matrix.length - 1;
        if (target > matrix[right][row]) {
            return false;
        }
        if (target < matrix[left][row]) {
            return false;
        }
        int middle = 0;
        while (left < right) {
            middle = left + ((right - left) >> 2);
            if (target == matrix[middle][row]) {
                return true;
            } else if (target > matrix[middle][row]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return matrix[left][row] == target;
    }
}
