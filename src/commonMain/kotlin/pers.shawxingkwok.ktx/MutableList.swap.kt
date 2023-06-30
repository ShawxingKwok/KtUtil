package pers.shawxingkwok.ktx

/**
 * Swap values between elements at [i] and [j].
 *
 * Usage example:
 *
 * ```
 * mutableListOf(1, 2, 3).apply{ swap(0, 2) } // 3, 2, 1
 */
public fun <E> MutableList<E>.swap(i: Int, j: Int){
    this[i] = this[j].also { this[j] = this[i] }
}