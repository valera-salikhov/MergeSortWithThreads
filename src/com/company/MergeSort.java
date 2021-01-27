package com.company;

public interface MergeSort {
    void merge(int[] left_array, int size_left, int[] right_array, int size_right, int[] ans, int left);
    void merge_sort(int[] arr, int left, int right);
}
