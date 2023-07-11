package com.wy.cybertodoadmin.base.until;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 算法小工具
 * @date 2023/7/10 17:25:47
 */
public class AlgorithmUtil {


    // =========================排序算法==========================

    /**
     *  选择排序 从小到大
     *  // 左边界逼近右边界，二次循环为边界循环，最后一次落到边界上，刹住车
     * @param arr 数组
     */
    public static void selectSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        // 一层
        for (int i = 0; i <= N - 1;i++){
            int min = i;
            for (int j = i + 1; j <= N-1; j++){
                min = arr[j] < arr[min]?j:min;
            }
            if (min != i){
                switchIntArr(arr,i,min);
            }
        }

    }

    /**
     * 冒泡排序 从小到大
     */
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        // 一层
        for(int end = N - 1; end >= 0; end--){
            for (int sec = 1; sec <= end; sec++){
                if (arr[sec-1] > arr[sec]){
                    switchIntArr(arr,sec-1,sec);
                }

            }
        }

    }

    /**
     * 交换工具类， 交换整形
     */
    private static void switchIntArr(int[] arr,int first, int secend){
            arr[first] ^= arr[secend];
            arr[secend] ^= arr[first];
            arr[first] ^= arr[secend];
    }


}
