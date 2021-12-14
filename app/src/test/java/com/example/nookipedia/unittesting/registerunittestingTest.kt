package com.example.nookipedia.unittesting

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class registerunittestingTest {
    private lateinit var validator:registerunittesting

    @Before
    fun setUp() {
        validator= registerunittesting()
    }

    @Test
    fun emailisvalidwithinvalidemailthenreturnfalsevalid()
    {
        val validation=validator.emailisvalid("test-test.com")


        assertEquals(false,validation)
    }

    @Test

    fun emailisvalidwithvalidemailthenreturntruevalid()
    {
        val validation=validator.emailisvalid("test@test.com")


        assertEquals(true,validation)
    }
}