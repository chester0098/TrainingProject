package com.fadineg.trainingproject.first.tests

import com.fadineg.trainingproject.first.tasks.ArraysTraining
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ArraysTrainingTest {
    private val arraysTraining = ArraysTraining()

    @Test
    fun sortTest() = with(arraysTraining) {
        assertTrue(Arrays.equals(intArrayOf(0, 1, 2, 3, 4), sort(intArrayOf(4, 2, 1, 3, 0))))
        assertTrue(Arrays.equals(intArrayOf(), sort(intArrayOf())))
        assertTrue(Arrays.equals(intArrayOf(1, 2, 3), sort(intArrayOf(1, 2, 3))))
        assertTrue(Arrays.equals(intArrayOf(10, 2001, 30220), sort(intArrayOf(2001, 10, 30220))))
    }


    @Test
    fun maxValueTest() = with(arraysTraining) {
        assertEquals(23, maxValue(23, 1, 4, 6, 14))
        assertEquals(0, maxValue(0, 0, 0))
        assertEquals(1, maxValue(1))
        assertEquals(-2, maxValue(-34, -10, -2))
    }

    @Test
    fun reverseTest() = with(arraysTraining) {
        assertTrue(Arrays.equals(intArrayOf(0, 1, 2), reverse(intArrayOf(2, 1, 0))))
        assertTrue(Arrays.equals(intArrayOf(0), reverse(intArrayOf(0))))
        assertTrue(Arrays.equals(intArrayOf(), reverse(intArrayOf())))
    }

    @Test
    fun fibonacciNumbersTest() = with(arraysTraining) {
        assertTrue(Arrays.equals(intArrayOf(), fibonacciNumbers(0)))
        assertTrue(Arrays.equals(intArrayOf(0), fibonacciNumbers(1)))
        assertTrue(Arrays.equals(intArrayOf(0, 1, 1, 2, 3), fibonacciNumbers(5)))
        assertTrue(
            Arrays.equals(
                intArrayOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89),
                fibonacciNumbers(12)
            )
        )
    }

    @Test
    fun maxCountSymbolTest() = with(arraysTraining) {
        assertEquals(3, maxCountSymbol(intArrayOf(1, 1, 1, 4, 5, 6, 3)))
        assertEquals(3, maxCountSymbol(intArrayOf(1, 1, 1, 2, 2, 2)))
        assertEquals(6, maxCountSymbol(intArrayOf(1, 1, 1, 1, 1, 1)))
        assertEquals(1, maxCountSymbol(intArrayOf(1, 2, 3, 4, 5, 6)))
        assertEquals(0, maxCountSymbol(intArrayOf()))
    }
}