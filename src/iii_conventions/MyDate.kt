package iii_conventions

import java.sql.Time

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override operator fun compareTo(other: MyDate): Int {
        return ((year - other.year) * 12 * 4 * 10) +
                ((month - other.month) * 4 * 10) +
                (dayOfMonth - other.dayOfMonth)
    }
}

operator fun MyDate.plus(ti: TimeInterval): MyDate = this.addTimeIntervals(ti, 1)
operator fun MyDate.plus(rti: RepeatedTimeInterval): MyDate = this.addTimeIntervals(rti.ti, rti.number)

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)

class RepeatedTimeInterval(val ti: TimeInterval, val number: Int)


class DateRange(val start: MyDate, val endInclusive: MyDate)

operator fun DateRange.contains(d: MyDate): Boolean = d >= start && d <= endInclusive

operator fun DateRange.iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
    private var prev: MyDate = start.addTimeIntervals(TimeInterval.DAY, -1)

    override fun next(): MyDate {
        prev = prev.nextDay()
        return prev
    }
    override fun hasNext(): Boolean {
        return prev.nextDay() <= endInclusive
    }
}
