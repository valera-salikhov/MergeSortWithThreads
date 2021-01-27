package com.company;

public class ThreadsForMergeSort extends Thread implements MergeSort{

    int [] arr;
    int left, right;
/*
    ThreadsForMergeSort(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

 */

    ThreadsForMergeSort(int[] arr, int left, int right) {
        this.arr = new int[arr.length];
        for (int i = left; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
        this.left = left;
        this.right = right;
    }

    public void merge(int[] left_array, int size_left, int[] right_array, int size_right, int[] ans, int left) {
        int index_left = 0;
        int index_right = 0;
        while (index_left < size_left && index_right < size_right) {
            if (left_array[index_left] < right_array[index_right]) {
                ans[left] = left_array[index_left];
                index_left++;
                left++;
                continue;
            }
            if (left_array[index_left] > right_array[index_right]) {
                ans[left] = right_array[index_right];
                index_right++;
                left++;
                continue;
            }
            if (left_array[index_left] == right_array[index_right]) {
                ans[left] = left_array[index_left];
                index_left++;
                left++;
                continue;
            }
        }
        while (index_right < size_right) {
            ans[left] = right_array[index_right];
            index_right++;
            left++;
        }
        while (index_left < size_left) {
            ans[left] = left_array[index_left];
            index_left++;
            left++;
        }
    }

    public void merge_sort(int[] arr, int left, int right) {
        if (right - left == 1) {
            return;
        }
        int medium = (left + right) / 2;
        merge_sort(arr, left, medium);
        merge_sort(arr, medium, right);
        int n = medium - left;
        int m = right - medium;
        int[] left_arr = new int[n];

        for (int i = 0; i < n; i++) {
            left_arr[i] = 0;
        }

        for (int i = 0, j = left; i < n; i++, j++) {
            left_arr[i] = arr[j];
        }
        int[] right_arr = new int[m];

        for (int i = 0; i < m; i++) {
            right_arr[i] = 0;
        }

        for (int i = 0, j = medium; i < m; i++, j++) {
            right_arr[i] = arr[j];
        }
        merge(left_arr, n, right_arr, m, arr, left);
    }

    @Override
    public void run() {
        merge_sort(arr, left, right);
    }

}
