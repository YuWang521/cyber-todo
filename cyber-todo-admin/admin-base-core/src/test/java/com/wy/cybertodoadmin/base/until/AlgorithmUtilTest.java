package com.wy.cybertodoadmin.base.until;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/10 17:34:09
 */
class AlgorithmUtilTest {

    @Test
    public void arr(){
        System.out.println("================ < =================");
        for (int i = 0;i < 10; i++){
            System.out.println("i: " + i);
        }
        System.out.println("================ <= =================");
        for (int i = 0;i <= 10; i++){
            System.out.println("i: " + i);
        }
        System.out.println("================ reserve <= =================");
        for (int i = 10;i >= 0; i--){
            System.out.println("i: " + i);
        }

        // 有等号，会执行下标次，比如start为n，end为m，下标会从由n变成m
        // 没有等号，倒在胜利之前，start为n，end为吗，则下标只会由n变为m-1
    }

    @Test
    public void testSelectSortPositiveCase() {
        int[] arr = {4, 2, 7, 1, 5};
        int[] expected = {1, 2, 4, 5, 7};
        AlgorithmUtil.selectSort(arr);
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testSelectSortNegativeCase() {
        int[] arr = {4, 2, -7, 1, 5};
        int[] expected = {-7, 1, 2, 4, 5};
        AlgorithmUtil.selectSort(arr);
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testSelectSortEmptyArray() {
        int[] arr = {};
        AlgorithmUtil.selectSort(arr);
        assertEquals(0, arr.length);
    }
    @Test
    public void testSelectSortSingleElement() {
        int[] arr = {5};
        AlgorithmUtil.selectSort(arr);
        assertEquals(1, arr.length);
        assertEquals(5, arr[0]);
    }

    @Test
    public void testBubbleSortPositive() {
        int[] arr = {5, 3, 8, 2, 1, 4};
        int[] expected = {1, 2, 3, 4, 5, 8};
        AlgorithmUtil.bubbleSort(arr);
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testBubbleSortNegative() {
        int[] arr = {5, 3, 8, 2, 1, 4};
        int[] expected = {1, 2, 3, 4, 8, 5};
        AlgorithmUtil.bubbleSort(arr);
        Assert.assertNotEquals(expected, arr);
    }
    @Test
    public void testBubbleSortEmptyArray() {
        int[] arr = {};
        AlgorithmUtil.bubbleSort(arr);
        Assert.assertEquals(new int[]{}, arr);
    }
    @Test
    public void testBubbleSortNullArray() {
        int[] arr = null;
        AlgorithmUtil.bubbleSort(arr);
        Assert.assertNull(arr);
    }


}