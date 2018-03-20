package iii_conventions

import iii_conventions.test.s
import org.junit.Assert.assertTrue
import org.junit.Test

class N25ComparisonKtTest {
    /* Month numbering starts with 0 (0-Jan, 1-Feb, ... 11-Dec) */
    @Test fun testDateComparison() {
        assertTrue(task25(MyDate(2014, 1, 1), MyDate(2014, 1, 2)))
    }

    @Test fun testBefore() {
        val first = MyDate(2014, 5, 10)
        val second = MyDate(2014, 7, 11)
        assertTrue("The date ${first.s} should be before ${second.s}", first < second)
    }

    @Test fun testDayBeforeNewYear() {
//        ((2016 - 2017) * 12 * 4 * 7) + ((11 - 0) * 4 * 7) + ((31 - 1))
        val first = MyDate(2016, 11, 31)
        val second = MyDate(2017, 0, 1)
        assertTrue("The date ${first.s} should be before ${second.s}", first < second)
    }

    @Test fun testAfter() {
        val first = MyDate(2014, 10, 20)
        val second = MyDate(2014, 7, 11)
        assertTrue("The date ${first.s} should be after ${second.s}", first > second)
    }

    /* If you declare 'compareTo' as an extension function, remove this one to make the code compile */
    operator fun MyDate.compareTo(other: MyDate): Int = todoTask25()
}