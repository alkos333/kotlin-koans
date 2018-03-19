package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    operator override fun compareTo(other: MyDate): Int {
        return ((year - other.year) * 365) +
                ((month - other.month) * 31) +
                (dayOfMonth - other.dayOfMonth)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)


enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    operator fun contains(d: MyDate): Boolean = d >= start && d <= endInclusive
    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate>{
        operator override fun next(): MyDate {
            println("Am I called?")
            return start.nextDay()
        }
        override fun hasNext(): Boolean = start.nextDay() <= endInclusive
    }
}
