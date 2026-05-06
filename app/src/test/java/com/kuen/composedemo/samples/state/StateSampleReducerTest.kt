package com.kuen.composedemo.samples.state

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StateSampleReducerTest {

    @Test
    fun increment_updates_count_without_effect() {
        val before = StateSampleUiState(count = 2, lastSavedSummary = null)
        val (after, effect) = reduceStateSample(before, StateSampleEvent.Increment)
        assertEquals(3, after.count)
        assertNull(effect)
    }

    @Test
    fun save_updates_summary_and_emits_snackbar_effect() {
        val before = StateSampleUiState(count = 5, lastSavedSummary = null)
        val (after, effect) = reduceStateSample(before, StateSampleEvent.Save)
        assertEquals(5, after.count)
        assertTrue(after.lastSavedSummary!!.contains("5"))
        assertTrue(effect is StateSampleEffect.ShowSavedSnackbar)
        assertEquals("已同步 count=5", (effect as StateSampleEffect.ShowSavedSnackbar).message)
    }
}
