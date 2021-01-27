package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int CountThreads = 2;
        ThreadsForMergeSort[] threads = new ThreadsForMergeSort[CountThreads];
        System.out.println("Введите количество элементов массива:");
        int n;
        n = sc.nextInt();
        System.out.println("Введите элементы массива:");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < CountThreads; i++) {
            threads[i] = new ThreadsForMergeSort(arr, i * (n / CountThreads),
                    (i + 1) * (n / CountThreads));
            threads[i].start();
        }
        for (int i = 0; i < CountThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // для двух потоков
        int[] copyArr = new int[n];
        MergeSort mergeSort = new MergeSort() {
            @Override
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
            @Override
            public void merge_sort(int[] arr, int left, int right) {}
        };

        int[] brr = new int[threads[0].right - threads[0].left + 1];
        int[] crr = new int[threads[0].right - threads[0].left + 1];

        for (int i = threads[0].left; i < threads[0].right; i++) {
            brr[i - threads[0].left] = threads[0].arr[i];
        }
        //System.out.println("####################");
        for (int i = threads[1].left ; i < threads[1].right; i++) {
            crr[i - threads[1].left] = threads[1].arr[i];
        }

/*
        mergeSort.merge(brr, threads[0].right - threads[0].left + 1, crr, threads[1].right - threads[1].left + 1, copyArr, 0);
        for (int i = 0; i < n; i++) {
            arr[i] = copyArr[i];
        }
        // вывод массива
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }

 */

    }
}
