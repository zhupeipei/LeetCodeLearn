package com.aire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2021/11/4 上午2:14.
 *
 * @Author ZhuPeipei
 */
public class Exercise295 {
    public static void main(String[] args) {
        Exercise295 test = new Exercise295();
//        int[] nums = new int[]{40, 12, 16, 14, 35, 19, 34, 35, 28, 35, 26, 6, 8, 2, 14, 25, 25, 4, 33, 18, 10, 14, 27, 3, 35, 13, 24, 27, 14, 5, 0, 38, 19, 25, 11, 14, 31, 30, 11, 31, 0};
//        int[] nums = new int[]{40, 12, 16, 14, 35, 19, 34, 35, 28, 35, 26, 6, 8, 2, 14, 25, 25};
        int[] nums = new int[]{1, 2, 3};
        for (int i = 0; i < nums.length; i++) {
            test.addNum(nums[i]);
            System.out.println(test.findMedian());
        }
    }

    private List<Integer> bigList = new ArrayList<>();
    private List<Integer> smallList = new ArrayList<>();

    // 295. 数据流的中位数
    public Exercise295() {
//        PriorityQueue
    }

    public void addNum(int num) {
        int bigSize = bigList.size();
        int smallSize = smallList.size();
        if (bigSize == 0 && smallSize == 0) {
            smallList.add(num);
            return;
        } else if (bigSize == 0 && smallSize == 1) {
            if (smallList.get(0) < num) {
                bigList.add(smallList.get(0));
                smallList.set(0, num);
            } else {
                bigList.add(num);
            }
            return;
        } else if (smallSize == 0 && bigSize == 1) {
            if (bigList.get(0) > num) {
                smallList.add(bigList.get(0));
                bigList.set(0, num);
            } else {
                smallList.add(num);
            }
            return;
        }

        int bigNum = bigList.get(0); // 4是list中最大的
        int smallNum = smallList.get(0); // 6是list最小的 bigger false
        if (bigSize > smallSize) {
            // 这里必须要放到smallList中了
            if (num >= bigNum) {
                smallList.add(num);
                heapifyFromFloor(smallList, false);
            } else {
                int firstNum = replaceNumAtFirst(bigList, num, true);
                smallList.add(firstNum);
                heapifyFromFloor(smallList, false);
            }
        } else if (bigSize < smallSize) {
            // 这里必须放到bigList中
            if (num <= smallNum) {
                bigList.add(num);
                heapifyFromFloor(bigList, true);
            } else {
                int firstNum = replaceNumAtFirst(smallList, num, false);
                bigList.add(firstNum);
                heapifyFromFloor(bigList, true);
            }
        } else {
            if (num >= smallNum) {
                smallList.add(num);
                heapifyFromFloor(smallList, false);
            } else {
                bigList.add(num);
                heapifyFromFloor(bigList, true);
            }
        }
    }

    // bigger 表示 数组是递减的
    private void heapifyFromFloor(List<Integer> list, boolean bigger) {
        int child = list.size() - 1; // 3 4 5 6 --- 1 1 2 2
        int middle = (child - 1) / 2; // 2 3 4 5 --- 1 1 2 2
        while (middle >= 0) {
            if (bigger) {
                if (list.get(middle) < list.get(child)) {
                    swap(list, middle, child);
                } else {
                    break;
                }
            } else {
                if (list.get(middle) > list.get(child)) {
                    swap(list, middle, child);
                } else {
                    break;
                }
            }
            child = middle;
            middle = (middle - 1) / 2;
        }
    }

    private int replaceNumAtFirst(List<Integer> list, int num, boolean bigger) {
        int replaceNum = list.get(0);

        list.set(0, num);
        heapify(list, 0, bigger);

        return replaceNum;
    }

    public double findMedian() {
        if (bigList.size() == smallList.size()) {
            return (bigList.get(0) + smallList.get(0)) / 2f;
        } else if (bigList.size() > smallList.size()) {
            return bigList.get(0);
        } else {
            return smallList.get(0);
        }
    }

    // bigList顶部元素的最大值是比smallList的最小值小的。
    public void addNum1(int num) {
        int bigSize = bigList.size();
        int smallSize = smallList.size();
        if (bigSize == 0 && smallSize == 0) {
            smallList.add(num);
            return;
        } else if (bigSize == 0 && smallSize == 1) {
            if (smallList.get(0) < num) {
                bigList.add(smallList.get(0));
                smallList.set(0, num);
            } else {
                bigList.add(num);
            }
            return;
        } else if (smallSize == 0 && bigSize == 1) {
            if (bigList.get(0) > num) {
                smallList.add(bigList.get(0));
                bigList.set(0, num);
            } else {
                smallList.add(num);
            }
            return;
        }

        int bigNum = bigList.get(0); // 4是list中最大的
        int smallNum = smallList.get(0); // 6是list最小的
        if (bigSize < smallSize) {
            // 要放到big里了
            if (num <= smallList.get(0)) {
                bigList.add(num);
                buildMaxHeap();
            } else {
                int small0 = smallList.get(0);
                bigList.add(small0);
                buildMaxHeap();
                smallList.set(0, num);
                heapify(smallList, 0, false);
            }
        } else if (bigSize > smallSize) {
            // 要放到small中
            if (num < bigList.get(0)) {
                int big0 = bigList.get(0);
                bigList.set(0, num);
                heapify(bigList, 0, true);
                smallList.add(big0);
                buildMinHeap();
            } else {
                smallList.add(num);
                buildMinHeap();
            }
        } else {
            if (num <= bigList.get(0)) {
                bigList.add(num);
                buildMaxHeap();
            } else if (num >= smallList.get(0)) {
                smallList.add(num);
                buildMinHeap();
            } else { // 随便选一个吧
                smallList.add(num);
                buildMinHeap();
            }
        }
    }

    private void buildMinHeap() {
        int size = smallList.size();
        if (size <= 0) {
            return;
        }
        int middle = size / 2;
        for (int i = middle + 1; i >= 0; i--) {
            heapify(smallList, i, false);
        }
    }

    // bigList的顶部数据是最大的元素
    private void buildMaxHeap() {
        int size = bigList.size();
        if (size <= 0) {
            return;
        }
        int middle = size / 2;
        for (int i = middle + 1; i >= 0; i--) {
            heapify(bigList, i, true);
        }
    }

    // bigger 表示 数组是递减的
    private void heapify(List<Integer> list, int index, boolean bigger) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int swapIndex = index;
        if (bigger) {
            if (left < list.size() && list.get(left) > list.get(index)) {
                swapIndex = left;
            }
            if (right < list.size() && list.get(right) > list.get(swapIndex)) {
                swapIndex = right;
            }
        } else {
            if (left < list.size() && list.get(left) < list.get(index)) {
                swapIndex = left;
            }
            if (right < list.size() && list.get(right) < list.get(swapIndex)) {
                swapIndex = right;
            }
        }
        if (swapIndex != index) {
            swap(list, index, swapIndex);
            heapify(list, swapIndex, bigger);
        }
    }

    private void swap(List<Integer> list, int left, int right) {
        int tmp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, tmp);
    }

    public double findMedian1() {
        if (bigList.size() == smallList.size()) {
            return (bigList.get(0) + smallList.get(0)) / 2f;
        } else if (bigList.size() > smallList.size()) {
            return bigList.get(0);
        } else {
            return smallList.get(0);
        }
    }
}
