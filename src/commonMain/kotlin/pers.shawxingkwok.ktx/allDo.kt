package pers.shawxingkwok.ktx

/**
 * Makes all [elements] of the nearest shared type [T] [act].
 *
 * Usage example:
 *
 * ```
 * allDo(1, 2, 3){ println(it) } // prints: 1, 2, 3
 */
public inline fun <T> allDo(vararg elements: T, act: (T) -> Unit){
    elements.forEach(act)
}