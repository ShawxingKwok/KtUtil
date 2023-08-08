package pers.shawxingkwok.ktutil

private fun Number.parseToOrder(): String{
    val str = toString()

    val suffix =
        if (str.getOrNull(str.lastIndex - 1) == '1')
            "th"
        else
            when(str.last()){
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
 * 21.toShort().toOrder() // 21st
 * 32.toShort().toOrder() // 32nd
 * 43.toShort().toOrder() // 43rd
 * 54.toShort().toOrder() // 54th
 */
public fun Short.toOrder(): String = parseToOrder()

/**
 * Returns a string with a right postfix after [this].
 *
 * Usage example:
 *
 * ```
 * 21.toOrder() // 21st
 * 32.toOrder() // 32nd
 * 43.toOrder() // 43rd
 * 54.toOrder() // 54th
 */
public fun Int.toOrder(): String = parseToOrder()

/**
 * Returns a string with a right postfix after [this].
 *
 * Usage example:
 * ```
 * 21L.toOrder() // 21st
 * 32L.toOrder() // 32nd
 * 43L.toOrder() // 43rd
 * 54L.toOrder() // 54th
 */
public fun Long.toOrder(): String = parseToOrder()