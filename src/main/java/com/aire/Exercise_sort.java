package com.aire;

import java.util.Arrays;

/**
 * Created on 2021/10/26 9:20 下午.
 *
 * @Author ZhuPeipei
 */
public class Exercise_sort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 堆排序
    private static void heapSort(int[] arr) {
        int len = arr.length;
        buildMaxHeap(arr);
        for (int i = len - 1; i >= 0; i--) {
            swap(arr, 0, i);
            headpify(arr, 0, i);
        }
    }

    // 建立大顶堆
    // 这里保证了 堆中的所有元素 父节点的值一定比子节点的值大
    private static void buildMaxHeap(int[] arr) {
        for (int i = arr.length / 2; i >= 0; i--) {
            headpify(arr, i, arr.length);
        }
    }

    private static void headpify(int[] arr, int index, int len) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;
        if (left < len && arr[largest] < arr[left]) {
            largest = left;
        }
        if (right < len && arr[largest] < arr[right]) {
            largest = right;
        }
        if (largest != index) {
            swap(arr, largest, index);
            headpify(arr, largest, len);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
