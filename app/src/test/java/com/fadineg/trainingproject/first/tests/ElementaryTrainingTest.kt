package com.fadineg.trainingproject.first.tests

import com.fadineg.trainingproject.first.tasks.ElementaryTraining
import org.junit.Assert.*

import org.junit.Test

class ElementaryTrainingTest {
    private val elementaryTraining = ElementaryTraining()

    @Test
    fun averageValueTest() = with(elementaryTraining) {
        assertEquals(2.0, averageValue(1, 3), 0.0)
        assertEquals(2.5, averageValue(2, 3), 0.0)
        assertEquals(0.0, averageValue(0, 0), 0.0)
        assertEquals(1.0, averageValue(1, 1), 0.0)
        assertEquals(0.0, averageValue(-1, 1), 0.0)
    }

    @Test
    fun complicatedAmountTest() = with(elementaryTraining) {
        assertEquals(1, complicatedAmount(1, 1, 1))
        assertEquals(-3, complicatedAmount(0, 0, 0))
        assertEquals(108, complicatedAmount(3, 5, 10))
        assertEquals(-7, complicatedAmount(-4, -5, -3))
    }

    @Test
    fun changeValueTest() = with(elementaryTraining) {
        assertEquals(20, changeValue(10))
        assertEquals(-10, changeValue(0))
        assertEquals(-7, changeValue(3))
        assertEquals(-11, changeValue(-1))
    }

    @Test
    fun swapNumbersTest() = with(elementaryTraining) {
        assertEquals(1, swapNumbers(1))
        assertEquals(1, swapNumbers(10))
        assertEquals(22, swapNumbers(220))
        assertEquals(123, swapNumbers(321))
        assertEquals(86571, swapNumbers(16578))
        assertEquals(-123, swapNumbers(-321))
    }

    @Test
    fun zeroEvenNumberTest() = with(elementaryTraining) {
        assertEquals(2, zeroEvenNumber(2))
        assertEquals(0, zeroEvenNumber(0))
        assertEquals(30, zeroEvenNumber(32))
        assertEquals(30000, zeroEvenNumber(32248))
        assertEquals(230, zeroEvenNumber(232))
    }
}