package com.fadineg.trainingproject.first.tests

import com.fadineg.trainingproject.first.tasks.StringsTraining
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class StringsTrainingTest {

    private val stringsTraining = StringsTraining()

    @Test
    fun oddCharacterTest() = with(stringsTraining) {
        assertEquals("135", getOddCharacterString("123456"))
        assertEquals("", getOddCharacterString(""))
    }

    @Test
    fun numberCountTest() = with(stringsTraining) {
        assertEquals(3, getNumbersCount("aaaa4fff56"))
        assertEquals(0, getNumbersCount("nonumbershere"))
        assertEquals(5, getNumbersCount("13152"))
        assertEquals(0, getNumbersCount(""))
    }

    @Test
    fun getArrayLastSymbol() = with(stringsTraining) {
        assertTrue(Arrays.equals(intArrayOf(2), getArrayLastSymbol("bbabba")))
        assertTrue(Arrays.equals(intArrayOf(0, 2, 5), getArrayLastSymbol("ababbaa")))
        assertTrue(Arrays.equals(intArrayOf(), getArrayLastSymbol("bbbbba")))
        assertTrue(Arrays.equals(intArrayOf(), getArrayLastSymbol("")))
    }


    @Test
    fun replaceAllNumbersTest() = with(stringsTraining) {
        assertEquals(
            "I know I'am not the only one",
            replaceAllNumbers("I know I'am not the only 1")
        )
        assertEquals("onetwothree", replaceAllNumbers("123"))
        assertEquals("nonumbershere", replaceAllNumbers("nonumbershere"))
        assertEquals("", replaceAllNumbers(""))
    }

    @Test
    fun capitalReverseTest() = with(stringsTraining) {
        assertEquals("AaAaAaAa", capitalReverse("aAaAaAaA"))
        assertEquals("aaa", capitalReverse("AAA"))
        assertEquals("AAA", capitalReverse("aaa"))
        assertEquals("aa11AA", capitalReverse("AA11aa"))
        assertEquals("", capitalReverse(""))
    }
}