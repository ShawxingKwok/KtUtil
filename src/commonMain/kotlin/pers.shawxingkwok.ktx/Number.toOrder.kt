package pers.shawxingkwok.ktx

private fun Number.parseToOrder(): String{
    val str = toString()
    val suffix = when(str.last()){
        '1' -> "st"
        '2' -> "nd"
        '3' -> "rd"
        else -> "th"
    }
    return "$str$suffix"
}

/**
 * Returns a string with a right postfix after [this].
 *
 * Usage example:
 *
 * ```
 * 11.toShort().toOrder() // 11st
 * 12.toShort().toOrder() // 12nd
 * 13.toShort().toOrder() // 13rd
 * 14.toShort().toOrder() // 14th
 */
public fun Short.toOrder(): String = parseToOrder()

/**
 * Returns a string with a right postfix after [this].
 *
 * Usage example:
 *
 * ```
 * 11.toOrder() // 11st
 * 12.toOrder() // 12nd
 * 13.toOrder() // 13rd
 * 14.toOrder() // 14th
 */
public fun Int.toOrder(): String = parseToOrder()

/**
 * Returns a string with a right postfix after [this].
 *
 * Usage example:
 * ```
 * 11L.toOrder() // 11st
 * 12L.toOrder() // 12nd
 * 13L.toOrder() // 13rd
 * 14L.toOrder() // 14th
 */
public fun Long.toOrder(): String = parseToOrder()